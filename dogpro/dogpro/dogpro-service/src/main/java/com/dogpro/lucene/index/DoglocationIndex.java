package com.dogpro.lucene.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import com.dogpro.domain.model.DogLocation;
import com.dogpro.lucene.util.DocumentUtils;
import com.dogpro.lucene.util.LuceneUtils;

public class DoglocationIndex {
	private LuceneUtils luceneUtils = new LuceneUtils();

	public void testCreateIndex(DogLocation dogLocation) throws Exception{
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					luceneUtils.getVersion(), luceneUtils.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(
					luceneUtils.getWriteDirectory(), indexWriterConfig);
			indexWriter.addDocument(DocumentUtils
					.DogLocation2Document(dogLocation));
			indexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 根据页码和分页大小获取上一次的最后一个scoredocs
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param query
	 * @param searcher
	 * @return
	 * @throws IOException
	 */
	private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query,
			IndexSearcher searcher) throws IOException {
		if (pageIndex == 1)
			return null;// 如果是第一页就返回空
		int num = pageSize * (pageIndex - 1);// 获取上一页的最后数量
		TopDocs tds = searcher.search(query, num);
		if (num - 1 < tds.scoreDocs.length) {
			ScoreDoc scoreDoc = tds.scoreDocs[num - 1];
			return scoreDoc;
		}else if(tds.scoreDocs.length>0){
			return tds.scoreDocs[tds.scoreDocs.length-1];
		}else{
			return null;
		}

	}

	public List<DogLocation> testSearchIndex(String text, int pageNo,
			int pageSize) throws Exception {
		List<DogLocation> dogLocations = new ArrayList<DogLocation>();
		try {
			String ss = QueryParser.escape(text);
			IndexReader[] indexReaders = new IndexReader[luceneUtils
					.getPathSize()];
			for (int i = 0; i < luceneUtils.getPathSize(); i++) {
				indexReaders[i] = DirectoryReader.open(luceneUtils
						.getReadDirectory(i));
			}
			MultiReader multiReader = new MultiReader(indexReaders);
			// IndexReader indexReader =
			// DirectoryReader.open(luceneUtils.getWriteDirectory());
			IndexSearcher indexSearcher = new IndexSearcher(multiReader);
			QueryParser queryParser = new MultiFieldQueryParser(
					luceneUtils.getVersion(), new String[] { "areaName",
							"addressAlias", "address" },
					luceneUtils.getAnalyzer());
			// queryParser.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query query = queryParser.parse(ss);
			FuzzyQuery fuzzyQuery1 = new FuzzyQuery(new Term("areaName", ss));
			FuzzyQuery fuzzyQuery2 = new FuzzyQuery(
					new Term("addressAlias", ss));
			FuzzyQuery fuzzyQuery3 = new FuzzyQuery(new Term("address", ss));
			BooleanQuery booleanQuery = new BooleanQuery();
			booleanQuery.add(query, Occur.SHOULD);
			booleanQuery.add(fuzzyQuery1, Occur.SHOULD);
			booleanQuery.add(fuzzyQuery2, Occur.SHOULD);
			booleanQuery.add(fuzzyQuery3, Occur.SHOULD);
			booleanQuery.setMinimumNumberShouldMatch(1);
			// 获取上一页的最后一个元素
			ScoreDoc lastSd = getLastScoreDoc(pageNo + 1, pageSize,
					booleanQuery, indexSearcher);
			// 通过最后一个元素去搜索下一页的元素
			TopDocs topDocs = indexSearcher.searchAfter(lastSd, booleanQuery,
					pageSize);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				DogLocation dogLocation = DocumentUtils
						.document2DogLocation(document);
				dogLocations.add(dogLocation);
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dogLocations;

	}

	/**
	 * 一般情况下索引库的删除用关键词
	 * 
	 * @throws Exception
	 */
	// 删除
	public void testDeleteIndex(Long id) throws Exception {
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					luceneUtils.getVersion(), luceneUtils.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(
					luceneUtils.getWriteDirectory(), indexWriterConfig);
			// indexWriter.deleteAll()删除所有的索引值
			/**
			 * term就为关键词对象 ID的索引保存类型为Index.NOT_ANALYZED,直接写ID也可以删除。
			 * title如果为Index.NOT_ANALYZED，那么关键词就不行，要整个内容才可以删除。
			 */
			Term term = new Term("id", id.toString());
			indexWriter.deleteDocuments(term);
			indexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 修改 先删除后增加 lucene的更新操作与数据库的更新操作是不一样的。
	 * 因为在更新的时候，有可能变换了关键字的位置，这样分词器对关键字还得重新查找，
	 * 而且还得在目录和内容中替换，这样做的效率比较低，所以lucene的更新操作是删除和增加两步骤来完成的。
	 */
	public void testUpdateIndex(DogLocation dogLocation) throws Exception {
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					luceneUtils.getVersion(), luceneUtils.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(
					luceneUtils.getWriteDirectory(), indexWriterConfig);
			Term term = new Term("id", dogLocation.getId().toString());

			/**
			 * term是用删除的 document是用于增加的
			 */
			indexWriter.updateDocument(term,
					DocumentUtils.DogLocation2Document(dogLocation));
			indexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
