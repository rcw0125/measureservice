package com.xgmes.interfaces;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.talent.core.util.ResourceUtils;
import com.xgmes.mapper.InterfaceNCMapper;
import com.xgmes.model.Datatransfer;
import com.xgmes.service.DatatransferService;
import com.xgmes.service.InterfaceService;

@Component
public class DownloadMointor {
	
	private final WatchService watcher;
	
	private final Map<WatchKey, Path> keys;
	
	private boolean trace = false;
	
	private String preFilePath = "";
	
	private List<Datatransfer> datatransferList;
	
	@Autowired
	private ResourceUtils resourceUtils;
	
	@Autowired
	private DatatransferService datatransferService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private InterfaceNCMapper interfaceNCMapper;
	
	@SuppressWarnings("unchecked")
	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}
	
	private void register(Path dir) throws IOException {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (trace) {
			Path prev = keys.get(key);
			if (prev == null) {
				System.out.format("新增注册: %s\n", dir);
			} else {
				if (!dir.equals(prev)) {
					System.out.format("更新注册: %s -> %s\n", prev, dir);
				}
			}
		}
		keys.put(key, dir);
	}
	
	private void registerAll(final Path start) throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				register(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	public DownloadMointor() throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();
	}

	@SuppressWarnings("rawtypes")
	private void processEvents() {
		for (;;) {
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			Path dir = keys.get(key);
			if (dir == null) {
				System.err.println("发现无效的接口监听路径！");
				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (kind == OVERFLOW) {
					continue;
				}
				WatchEvent<Path> ev = cast(event);
				Path name = ev.context();
				Path child = dir.resolve(name);
				if(kind == ENTRY_MODIFY){
					if(preFilePath.equals(child.toString())){
						preFilePath = "";
						continue;
					}else{
						preFilePath = child.toString();
					}
				}
				doDatatransfer(kind.name(),child);
				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(child,NOFOLLOW_LINKS)) {
							registerAll(child);
						}else{
							preFilePath = child.toString();
						}
					}catch (Exception x){
						
					}
				}
			}
			boolean valid = key.reset();
			if (!valid) {
				keys.remove(key);
				if (keys.isEmpty()) {
					break;
				}
			}
		}
	}
	
	private void doDatatransfer(String datatype,Path path){
		try{
			if("ENTRY_CREATE".equals(datatype)){
				System.out.format("%s: %s\n",datatype,path);
				String fileName = path.toFile().getAbsolutePath();
				if(fileName.endsWith(".xml")){
					interfaceNCMapper.insertTrainTask(fileName);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay=1555200000000l,initialDelay=2000)//每10个月执行一次，相当于只执行一次，延迟5秒执行。
    public void onStart() {
		Path dir = Paths.get(resourceUtils.getResource("ftp.interfaces.in.path"));
		try {
			System.out.println("开始启动数据同步接口.................");
			System.out.format("注册扫描路径： %s\n", dir);
			registerAll(dir);
			System.out.format("注册扫描路径：%s完成\n", dir);
			datatransferList = datatransferService.findAll();
			this.trace = true;
			processEvents();
			System.out.println("启动数据同步接口完成.................");
		} catch (IOException e) {
			System.out.println("数据同步接口启动失败.................");
		}
	}
	
	@Scheduled(fixedDelay=300000,initialDelay=2000)//每5分钟执行一次，相当于只执行一次，延迟5秒执行。
	public void onWriteBill() {
		try {
			System.out.println("开始同步火运发运单数据.................");
			List<Map<String,String>> tasks = interfaceNCMapper.queryTrainTask();
			for(Map<String,String> task : tasks){
				try{
					FileInputStream in = new FileInputStream(new File(task.get("FILEXMLNAME")));
					SAXReader reader = new SAXReader();
					reader.setEncoding("GB2312");
					interfaceService.InterfaceDataDecode(reader.read(in),task.get("FILEXMLNAME"), datatransferList);
				}catch(Exception ee){
					ee.printStackTrace();
				}				
			}			
			System.out.println("结束同步火运发运单数据.................");
		} catch (Exception e) {
			System.out.println("火运发运单数据同步失败.................");
		}
	}
}
