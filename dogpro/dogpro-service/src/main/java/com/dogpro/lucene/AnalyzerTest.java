
package com.dogpro.lucene;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 * 
 * @ClassName: AnalyzerTest
 * @Description: 各个分词器测试比较
 * @author weisd
 * @date 2017-3-28 下午3:24:34
 *
 */
public class AnalyzerTest {
    //要分词的字符串
    private static String str = "保利萨芬的建安费哈两极分化拉丝粉贺拉斯发货快拉夫";
    
    /**
     * 
     * @Title: print
     * @Description: 对各个分词技术分词结果进行输出
     * @author weisd
     * @time 2017-3-28 下午3:27:10
     */
    public static void print(Analyzer analyzer){
        StringReader reader = new StringReader(str);
        //使用分词器中的TokenStream 进行分词
        try {
            TokenStream tokenStream = analyzer.tokenStream("", reader);
            tokenStream.reset();
            //下面获取分词结果的CharTermAttribute
            CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println("分词技术："+analyzer.getClass());
            while (tokenStream.incrementToken()) {
                System.out.print(term.toString()+"|");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        Analyzer analyzer = null;
        //标准分词
        
        
        //空格分词
        analyzer = new WhitespaceAnalyzer(Version.LUCENE_47);
        AnalyzerTest.print(analyzer);
        //
        analyzer = new SimpleAnalyzer(Version.LUCENE_47);
        AnalyzerTest.print(analyzer);
        
        analyzer = new CJKAnalyzer(Version.LUCENE_47);
        AnalyzerTest.print(analyzer);
        
        analyzer = new KeywordAnalyzer();
        AnalyzerTest.print(analyzer);
        
        analyzer = new StopAnalyzer(Version.LUCENE_47);
        AnalyzerTest.print(analyzer);
        
        analyzer = new StandardAnalyzer(Version.LUCENE_47);
        AnalyzerTest.print(analyzer);
        //中文分词 IK
        
        analyzer = new IKAnalyzer(true);
        AnalyzerTest.print(analyzer);
        
        analyzer = new IKAnalyzer(false);
        AnalyzerTest.print(analyzer);
    }
}