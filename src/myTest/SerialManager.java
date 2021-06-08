package myTest;

/**
 * 
 * @author C_try
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.lang.Thread;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.basic.resources.basic;
import myTest.*;

//import myTest.GoBack;
import exception.SendDataToSerialPortFailure;
import exception.SerialPortInputStreamCloseFailure;
import exception.SerialPortOutputStreamCloseFailure;
import exception.SerialPortParameterFailure;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import jdk.nashorn.internal.ir.BreakableNode;

//
public class SerialManager implements SerialPortEventListener{

	private static SerialPort serialPort;
	private static SerialManager serialManager = null;
	private String recieveData;
	static {
		// 
		if (serialManager == null) {
			serialManager = new SerialManager();
		}
	}

	// 
	private SerialManager() {
	}

	/**
	 * 
	 * 
	 * @return serialTool
	 */
	public static SerialManager getSerialTool() {
		if (serialManager == null) {
			serialManager = new SerialManager();
		}
		return serialManager;
	}

	/**
	 * 
	 * 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static final ArrayList<String> findPort() {

		// 识别端口
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier
				.getPortIdentifiers();

		ArrayList<String> portNameList = new ArrayList<String>(); // add
																	// serialName
																	// to List
																	// and
																	// Return
																	// List
		while (portList.hasMoreElements()) {
			String portName = portList.nextElement().getName();
			portNameList.add(portName);
		}

		return portNameList;

	}

	/**
	 
	 参数：串口名、波特率
	 */
	public static final SerialPort openPort(String portName, int baudrate)
			throws Exception {

		try {

			// 通过端口名识别端口
			CommPortIdentifier portIdentifier = CommPortIdentifier
					.getPortIdentifier(portName);
			// 打开端口，
			// 设置端口名和超时时间
			CommPort commPort = portIdentifier.open(portName, 2000);
			// 判断是不是端口
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;

				try {
					// 设置串口波特率等参数
					serialPort.setSerialPortParams(baudrate,
							SerialPort.DATABITS_8, // 数据位
							SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); //停止位 校验位
				} catch (UnsupportedCommOperationException e) {
					throw new SerialPortParameterFailure();
				}

				//System.out.println("锟津开★拷COM " + portName);
				return serialPort;

			} else {
				// 
				throw new Exception("error");//
			}
		} catch (NoSuchPortException e1) {
			throw new NoSuchPortException();
		} catch (PortInUseException e2) {
			throw new PortInUseException();
		}
	}

    /**

     * @param serialPort
     *
     */
	public static void closePort(SerialPort serialPort) {
		if (serialPort != null) {
			serialPort.close();
			serialPort = null;
		}
	}


	/**
	 * 
	 * @param serialPort 
	 * @param order 待发送数据
	 * @throws SendDataToSerialPortFailure
	 * @throws SerialPortOutputStreamCloseFailure
	 */
	// 发送数据
	public static void sendToPort(SerialPort serialPort, byte[] order)
			throws SendDataToSerialPortFailure,
			SerialPortOutputStreamCloseFailure {

		OutputStream out = null;
		try {
			out = serialPort.getOutputStream();
			if (out != null) {
				out.write(order);
				out.flush();
			}
		} catch (IOException e) {
			throw new SendDataToSerialPortFailure();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				throw new SerialPortOutputStreamCloseFailure();
			}
		}

	}
	
	//读取数据
	
	public static byte[] readFromPort(SerialPort serialPort) {
		InputStream in = null;
		byte[] bytes = {};
		try {
			in = serialPort.getInputStream();
			System.out.println("r");
			byte[] readBuffer = new byte[1];
			int bytesNum = in.read(readBuffer);
			System.out.println("re");
			while (bytesNum > 0) {
				bytes = new byte[bytesNum];
				in.read(bytes);
				bytesNum = in.read(readBuffer);
				System.out.println("read"+bytesNum);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return  bytes;
	}

	// 接收前端命令
	
	public static void operateHand(String para, String speed) throws Exception {
		int num = 1;
		//byte[] bytes= {};
		if (!para.equals("N") && !para.equals("M") && !para.equals("P")
				&& !para.equals("O") && !para.equals("K") && !para.equals("L")
				&& !para.equals("B"))
			num = Integer.parseInt(speed);
		
		
		while (num > 0) {
			num = num - 1;
			serialPort = SerialManager.openPort("COM1", 115200);
			SerialManager.sendToPort(serialPort, para.getBytes());
			//SerialPortEvent serialPortEvent;
			//serialManager.serialEvent(serialPortEvent);
			SerialManager.closePort(serialPort);
		}
	}
	
	public static void getStatus()throws Exception {
		    
			serialPort = SerialManager.openPort("COM1", 115200);
			
			SerialManager.closePort(serialPort);
		
	}
	

	
	public static void setListenerToSerialPort(SerialPort serialPort,SerialPortEventListener listener) {
		try {
			serialPort.addEventListener(listener);
		}catch (TooManyListenersException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		serialPort.notifyOnDataAvailable(true);
		serialPort.notifyOnBreakInterrupt(true);
	}
	
	
	/* public static void  addListener(SerialPort serialPort,DataAvailableListener listener ) {
	
		try {
			serialPort.addEventListener(new SerialPortListener(listener));
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnBreakInterrupt(true);
		}catch(TooManyListenersException e) {
			e.printStackTrace();
		}
	}*/
	 public void serialEvent(SerialPortEvent serialPortEvent) {
		 
			switch (serialPortEvent.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE: // 1.涓插彛瀛樺湪鏈夋晥鏁版嵁
				byte [] data=null;
				data=readFromPort(serialPort);
				recieveData=new String(data);
				System.out.println("recieve"+recieveData);
				break;

			case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.杈撳嚭缂撳啿鍖哄凡娓呯┖
				break;

			case SerialPortEvent.CTS: // 3.娓呴櫎寰呭彂閫佹暟鎹�
				break;

			case SerialPortEvent.DSR: // 4.寰呭彂閫佹暟鎹噯澶囧ソ浜�
				break;

			case SerialPortEvent.RI: // 5.鎸搩鎸囩ず
				break;

			case SerialPortEvent.CD: // 6.杞芥尝妫�娴�
				break;

			case SerialPortEvent.OE: // 7.婧綅锛堟孩鍑猴級閿欒
				break;

			case SerialPortEvent.PE: // 8.濂囧伓鏍￠獙閿欒
				break;

			case SerialPortEvent.FE: // 9.甯ч敊璇�
				break;

			case SerialPortEvent.BI: // 10.閫氳涓柇
				System.out.println("error");
				break;

			default:
				break;
			}
		}
	/*public static class SerialPortListener implements SerialPortEventListener {

		private DataAvailableListener mDataAvailableListener;

		public SerialPortListener(DataAvailableListener mDataAvailableListener) {
			this.mDataAvailableListener = mDataAvailableListener;
		}

		public void serialEvent(SerialPortEvent serialPortEvent) {
			switch (serialPortEvent.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE: // 1.涓插彛瀛樺湪鏈夋晥鏁版嵁
				//SerialManager.readFromPort(serialPort);
				break;

			case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.杈撳嚭缂撳啿鍖哄凡娓呯┖
				break;

			case SerialPortEvent.CTS: // 3.娓呴櫎寰呭彂閫佹暟鎹�
				break;

			case SerialPortEvent.DSR: // 4.寰呭彂閫佹暟鎹噯澶囧ソ浜�
				break;

			case SerialPortEvent.RI: // 5.鎸搩鎸囩ず
				break;

			case SerialPortEvent.CD: // 6.杞芥尝妫�娴�
				break;

			case SerialPortEvent.OE: // 7.婧綅锛堟孩鍑猴級閿欒
				break;

			case SerialPortEvent.PE: // 8.濂囧伓鏍￠獙閿欒
				break;

			case SerialPortEvent.FE: // 9.甯ч敊璇�
				break;

			case SerialPortEvent.BI: // 10.閫氳涓柇
				System.out.println("error");
				break;

			default:
				break;
			}
		}
	}*/

	public interface DataAvailableListener {
		
		void dataAvailable();
	}

}

