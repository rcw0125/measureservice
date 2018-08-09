package com.talent.base.job;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.talent.base.util.ResourceUtils;

public class IndexLogsJob implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

		IndexWriter writer = null;
		
		try {
			Directory directory = FSDirectory.open(Paths.get(ResourceUtils.getResource("log.index.dir")));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

			writer = new IndexWriter(directory, iwc);
			Document document = null;
			File f = new File(ResourceUtils.getResource("log.store.dir"));
			for (File file : f.listFiles()){
				System.out.println("filename:" + file.getName());
				document = new Document();
				document.add(new LongField("modified", f.lastModified(), Field.Store.NO));
				document.add(new TextField("contents", new FileReader(file)));
				document.add(new StringField("path", file.toString(), Field.Store.YES));
				writer.addDocument(document);
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
		return RepeatStatus.FINISHED;
	}
}