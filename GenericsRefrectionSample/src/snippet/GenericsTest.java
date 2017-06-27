package snippet;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class GenericsTest {
	/**
	 * ターゲットのオブジェクトから総称型のクラスを取得するコードのテストケースです。<BR>
	 * 【試験条件】<BR>
	 * ターゲットのクラスは親クラスがあり、ターゲットのクラスおよび親クラスはそれぞれ総称型を含む別のインタフェースを実装しています。<BR>
	 * この状況でターゲットのクラスが直接実装している総称型を含むインタフェースの総称型クラスのみを取得します。
	 */
	@Test
	public void test() {
		try {
			TestIfImpl target = new TestIfImpl();
			Type[] types = null;
			// ターゲットのクラスが直接実装しているインタフェースを取得
			// CglibProxyだったら親クラスから取得
			if (ClassUtils.isCglibProxy(target)) {
				types = target.getClass().getSuperclass().getGenericInterfaces();
			} else {
				types = target.getClass().getGenericInterfaces();
			}
			for (Type type : types) {
				// インタフェースが総称型を含むもののみ抽出
				if (type instanceof ParameterizedType) {
					// 実際に実装クラスで総称型に指定された型を取得
					Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
					for (Type actualType : actualTypes) {
						if (actualType instanceof ParameterizedType)
							// 総称型に指定されたクラスがさらに総称型を含む場合、
							// 以下のコードはうまく動かないのでとりあえずcontinue
							continue;
						// 総称型に指定されたクラスを取得
						Class<?> genericsClazz = Class.forName(actualType.getTypeName());
						System.out.println(target.getClass() + " の総称型を含むインタフェースは " + type.getTypeName()
								+ " その総称型に指定されたクラスは " + genericsClazz);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 実装クラスが直接実装するインタフェース。
	 *
	 * @param <T>
	 */
	public interface TestIf<T> {
		void process(T param) throws Exception;

	}

	/**
	 * 実装クラス。
	 *
	 */
	public static class TestIfImpl extends ParentTest implements TestIf<String>, Serializable {

		private static final long serialVersionUID = 8490109770949105435L;

		@Override
		public void process(String param) throws Exception {
			System.out.println(param);
		}

	}

	/**
	 * 実装クラスの親クラスが実装するインタフェース
	 *
	 */
	public interface ParentTestIf<T> {
		T xxx();
	}

	/**
	 * 実装クラスの親クラス。
	 *
	 */
	public static class ParentTest implements Runnable, ParentTestIf<Boolean> {
		@Override
		public void run() {

		}

		@Override
		public Boolean xxx() {
			return false;
		}
	}
}
