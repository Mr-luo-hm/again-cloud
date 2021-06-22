import com.again.cloud.web.WebStartApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = WebStartApplication.class)
@RunWith(SpringRunner.class)
public class SingletonTest {

	/**
	 * 懒汉式单例 类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例
	 */
	public static class LazySingleton {

		private static volatile LazySingleton instance = null; // 保证 instance 在所有线程中同步

		private LazySingleton() {
		} // private 避免类在外部被实例化

		public static synchronized LazySingleton getInstance() {
			// getInstance 方法前加同步
			if (instance == null) {
				instance = new LazySingleton();
			}
			return instance;
		}

	}

	/**
	 * 饿汉式单例 类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
	 */
	public static class HungrySingleton {

		private static final HungrySingleton instance = new HungrySingleton();

		private HungrySingleton() {
		}

		public static HungrySingleton getInstance() {
			return instance;
		}

	}

	public static class SingletonLazy {

		public static void main(String[] args) {
			President zt1 = President.getInstance();
			zt1.getName(); // 输出总统的名字
			President zt2 = President.getInstance();
			zt2.getName(); // 输出总统的名字
			if (zt1 == zt2) {
				System.out.println("他们是同一人！");
			}
			else {
				System.out.println("他们不是同一人！");
			}
		}

	}

	static class President {

		private static volatile President instance = new President(); // 保证instance在所有线程中同步

		// private避免类在外部被实例化
		private President() {
			System.out.println("产生一个总统！");
		}

		public static synchronized President getInstance() {
			// 在getInstance方法上加同步
			if (instance == null) {
				instance = new President();
			}
			else {
				System.out.println("已经有一个总统，不能产生新总统！");
			}
			return instance;
		}

		public void getName() {
			System.out.println("我是美国总统：特朗普。");
		}

	}

	@Test
	public void testSingleton() {
		HungrySingleton instance = HungrySingleton.getInstance();
		System.out.println(instance);
	}

}
