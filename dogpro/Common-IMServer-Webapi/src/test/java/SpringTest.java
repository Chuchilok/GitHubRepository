import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dogpro.dao.UserMapper;
import com.dogpro.domain.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mybatis.xml","classpath:spring-mvc.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class SpringTest {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void t(){
		try {
			User user = new User();
			user.setPhone("13144126222");
			user.setPswd("1234");
			Calendar calendar = Calendar.getInstance();
			user.setAddtimes(calendar.getTime());
			user.setUpdatetimes(calendar.getTime());
			user.setState(1);
			user.setIscompleted(0);
			userMapper.insert(user);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
