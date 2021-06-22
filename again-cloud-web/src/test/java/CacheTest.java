import com.again.cloud.biz.entity.SysUser;
import com.again.cloud.web.WebStartApplication;
import com.again.cloud.web.service.SysUserService;
import com.again.cloud.web.service.api.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = WebStartApplication.class)
@RunWith(SpringRunner.class)
public class CacheTest {

	@Resource
	SysUserService sysUserService;

	@Test
	public void cacheTest() {
		SysUser aaaa = sysUserService.getByUsername("aaaa");
		System.out.println(aaaa);
	}

}
