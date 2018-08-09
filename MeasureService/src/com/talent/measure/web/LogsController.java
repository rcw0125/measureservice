package com.talent.measure.web;

import com.talent.base.lucence.CommaAnalyzer;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.ResourceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogsController extends BaseController{
	
	private static String LINEENDER = "<<<<<";
	
	@RequestMapping(value = "/log/query.do")
	public String query(String keywords,String type,ModelMap model) {
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		if(null != keywords){
			try{
		        Directory dir = null;
		        String dirPath = "";
		        if("servicelog".equals(type)){
		        	dirPath = ResourceUtils.getResource("log.index.service.dir");
		        }else if("agentlog".equals(type)){
		        	dirPath = ResourceUtils.getResource("log.index.agent.dir");
		        }else{
		        	dirPath = ResourceUtils.getResource("log.index.client.dir");
		        }
		        
		        dir = FSDirectory.open(Paths.get(dirPath));
		        
		        IndexReader reader = DirectoryReader.open(dir);
		        IndexSearcher is = new IndexSearcher(reader);
		        Query query = new WildcardQuery(new Term("content","*"+keywords+"*"));
		        TopDocs topDocs = is.search(query, 1000); 
		        model.addAttribute("resultCount", topDocs.totalHits);
		        ScoreDoc[] hits = topDocs.scoreDocs;
		        Map<String,String> result = null;
		        String preLine = "";
		        for (ScoreDoc scoreDoc : hits) {
		            Document document = is.doc(scoreDoc.doc);
		            try{
		            	String[] lines = document.get("content").split(LINEENDER);
						for(String line : lines){
							if(line.indexOf(keywords) > -1 && !preLine.equals(line)){
								result = new HashMap<String,String>();
					            result.put("path", document.get("path"));
					            result.put("filename", document.get("path"));
					            result.put("content",line);
								results.add(result);
								preLine = line;
							}
						}
		            }catch(Exception e){
		            	
		            }
		        }
		        reader.close();
		        dir.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}else{
			model.addAttribute("resultCount",0);
		}
		model.addAttribute("searchResults", results);
		model.addAttribute("reporttype", type);
		return "logitems";
	}
	
	@ResponseBody
	@RequestMapping(value = "/log/index.do")
	public Message index(ModelMap model) {
		Message bm = new Message();
		IndexWriter writer = null;
		
		try {
			Directory directory = null;
			String[] dirtypes = new String[]{"service","agent","client"};
			for(String dirtype : dirtypes){
				directory = FSDirectory.open(Paths.get(ResourceUtils.getResource("log.index."+dirtype+".dir")));
				
				Analyzer analyzer = new CommaAnalyzer();
				IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

				writer = new IndexWriter(directory, iwc);
				Document document = null;
				File f = new File(ResourceUtils.getResource("log.store."+dirtype+".dir"));
				File[] files = f.listFiles();
				if(null != files){
					for (File file : files){
						document = new Document();
						document.add(new LongField("modified", f.lastModified(), Field.Store.YES));
						document.add(new TextField("content",readFile(file).toString(),Field.Store.YES));
						document.add(new StringField("path", file.toString(),Field.Store.YES));
						writer.addDocument(document);
					}
				}
				
				if (writer != null) {
					writer.close();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			if (writer != null) {
				try{
					writer.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bm;
	}
	
	public StringBuffer readFile(File file){
	    StringBuffer sb=new StringBuffer();
	    try {
	    	FileInputStream fis = new FileInputStream(file);
	    	InputStreamReader isr = new InputStreamReader(fis,"GBK");
			BufferedReader reader=new BufferedReader(isr);
			String str;
			while((str=reader.readLine())!=null){
				sb.append(str);
				sb.append(LINEENDER);
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return sb;
	}
}
