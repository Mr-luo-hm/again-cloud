import java.util.HashMap;
import java.util.Scanner;

public class Realizetype implements Cloneable {

	Realizetype() {
		System.out.println("具体原型创建成功！");
	}

	public Object clone() throws CloneNotSupportedException {
		System.out.println("具体原型复制成功！");
		return super.clone();
	}

}

class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Realizetype obj1 = new Realizetype();
		Realizetype obj2 = (Realizetype) obj1.clone();
		System.out.println("obj1==obj2?" + (obj1 == obj2));
	}

}

/*
 * 原型模式通常适用于以下场景。 对象之间相同或相似，即只是个别的几个属性不同的时候。 对象的创建过程比较麻烦，但复制比较简单的时候。
 */

// 带原型管理器的原型模式
interface Shape extends Cloneable {

	Object clone(); // 拷贝

	void countArea(); // 计算面积

}

class Circle implements Shape {

	public Object clone() {
		Circle w = null;
		try {
			w = (Circle) super.clone();
		}
		catch (CloneNotSupportedException e) {
			System.out.println("拷贝圆失败!");
		}
		return w;
	}

	public void countArea() {
		int r = 0;
		System.out.print("这是一个圆，请输入圆的半径：");
		Scanner input = new Scanner(System.in);
		r = input.nextInt();
		System.out.println("该圆的面积=" + 3.1415 * r * r + "\n");
	}

}

class Square implements Shape {

	public Object clone() {
		Square b = null;
		try {
			b = (Square) super.clone();
		}
		catch (CloneNotSupportedException e) {
			System.out.println("拷贝正方形失败!");
		}
		return b;
	}

	public void countArea() {
		int a = 0;
		System.out.print("这是一个正方形，请输入它的边长：");
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		System.out.println("该正方形的面积=" + a * a + "\n");
	}

}

class ProtoTypeManager {

	private final HashMap<String, Shape> ht = new HashMap<>();

	public ProtoTypeManager() {
		ht.put("Circle", new Circle());
		ht.put("Square", new Square());
	}

	public void addshape(String key, Shape obj) {
		ht.put(key, obj);
	}

	public Shape getShape(String key) {
		Shape temp = ht.get(key);
		return (Shape) temp.clone();
	}

}

class ProtoTypeShape {

	public static void main(String[] args) {
		ProtoTypeManager pm = new ProtoTypeManager();
		Shape obj1 = pm.getShape("Circle");
		obj1.countArea();
		Shape obj2 = pm.getShape("Square");
		obj2.countArea();
	}

}