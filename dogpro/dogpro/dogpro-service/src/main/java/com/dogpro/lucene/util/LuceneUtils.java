package com.dogpro.lucene.util;

import java.io.File;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;
import com.dogpro.common.tool.MessageConsumerConfig;

public class LuceneUtils {
	public  Directory[] readDirectory = null;
	public  Directory writeDirectory = null;
	public  Analyzer analyzer = null;
	public  IndexWriterConfig[] RindexWriterConfig = null;
	public  IndexWriterConfig WindexWriterConfig = null;
	public  Version version = Version.LUCENE_47;
	public  String writePath;
	public  int pathSize;
	
	public LuceneUtils(){
		try {
			Map packagesMap = MessageConsumerConfig.readConfig("config.properties");
//测试用
//			String paths = "C:\\Users\\Administrator\\Desktop\\tmp2\\testindex;C:\\Users\\Administrator\\Desktop\\tmp2\\testindex2;C:\\Users\\Administrator\\Desktop\\tmp2\\testindex3";
//					writePath = "C:\\Users\\Administrator\\Desktop\\tmp2\\testindex";
			
			String paths = packagesMap.get("lucenepathlist").toString().trim();
			writePath = packagesMap.get("luceneWritePath").toString().trim();
			//路径以 ; 分割
			String path[] = paths.split(";");
			pathSize = path.length;
			analyzer = new IKAnalyzer(false);
//			analyzer = new StandardAnalyzer(version);
			RindexWriterConfig = new IndexWriterConfig[pathSize];
			//读
			readDirectory = new Directory[pathSize];
			for(int i =0;i<pathSize;i++){
				RindexWriterConfig[i] = new IndexWriterConfig(version, analyzer);
				readDirectory[i] = FSDirectory.open(new File(path[i]));
				if(!path[i].equals(writePath)){
					IndexWriter indexWriters = new IndexWriter(readDirectory[i], RindexWriterConfig[i]);
					indexWriters.close();
				}
			}
			
			//写
			WindexWriterConfig = new IndexWriterConfig(version, analyzer);
			writeDirectory = FSDirectory.open(new File(writePath));
			IndexWriter indexWriter = new IndexWriter(writeDirectory, WindexWriterConfig);
			indexWriter.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Directory getReadDirectory(int index) {
		return readDirectory[index];
	}

	public void setReadDirectory(Directory[] readDirectory) {
		this.readDirectory = readDirectory;
	}

	public Directory getWriteDirectory() {
		return writeDirectory;
	}

	public void setWriteDirectory(Directory writeDirectory) {
		this.writeDirectory = writeDirectory;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public IndexWriterConfig getRindexWriterConfig(int index) {
		return RindexWriterConfig[index];
	}

	public void setRindexWriterConfig(IndexWriterConfig[] rindexWriterConfig) {
		RindexWriterConfig = rindexWriterConfig;
	}

	public IndexWriterConfig getWindexWriterConfig() {
		return WindexWriterConfig;
	}

	public void setWindexWriterConfig(IndexWriterConfig windexWriterConfig) {
		WindexWriterConfig = windexWriterConfig;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getWritePath() {
		return writePath;
	}

	public void setWritePath(String writePath) {
		this.writePath = writePath;
	}

	public int getPathSize() {
		return pathSize;
	}

	public void setPathSize(int pathSize) {
		this.pathSize = pathSize;
	}

	
}
