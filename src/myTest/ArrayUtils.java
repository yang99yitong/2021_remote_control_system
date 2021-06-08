package myTest;

public class ArrayUtils {
	/**
	 * 鍚堝苟鏁扮粍
	 * 
	 * @param firstArray
	 *            绗竴涓暟缁�
	 * @param secondArray
	 *            绗簩涓暟缁�
	 * @return 鍚堝苟鍚庣殑鏁扮粍
	 */
	public static byte[] concat(byte[] firstArray, byte[] secondArray) {
		if (firstArray == null || secondArray == null) {
			return null;
		}
		byte[] bytes = new byte[firstArray.length + secondArray.length];
		System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
		System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);
		return bytes;
	}
}

