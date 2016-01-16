package com.service;

import android.app.Service;

public class server extends base {
	
	public void onCreate()  
	{  
	    super.onCreate();  
		System.out.println("Service is created");  
	    new Thread()  
	    {  
	        public void run()  
	        {  
	            doListen();  
	        };  
	    }.start();  
	}
	
	public void onDestory()  
	{  
		System.out.println("Service is destroyed");  
	    super.onDestroy();  
	    //�ر��߳�  
	    mainThreadFlag = false;  
	    ioThreadFlag = false;  
	        //�رշ�����  
	    try  
	    {  
	    	mServerSocket.close();  
	    }catch (java.io.IOException e)  
	    {  
	        e.printStackTrace();  
	    }  
	}  
	 
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	
	private java.net.ServerSocket mServerSocket = null;  
	final int SERVER_PORT = 10086;
	private int currentId = 0;   
	public static Boolean mainThreadFlag = true;  
	public static Boolean ioThreadFlag = true; 
	    
	private void doListen()  
	{  
		System.out.println("service do listen begin"); 
	    mServerSocket = null;  
	    try  
	    {  
	        mServerSocket = new java.net.ServerSocket(SERVER_PORT);  
	        mainThreadFlag = true;  
	        while (mainThreadFlag)  
	        {  //������û�����Կͻ��˵����ӣ�û������һֱ����������  
	            java.net.Socket client = mServerSocket.accept();
	            System.out.println("socket accept new client");
	            new Thread(new IOSocket(handler, client)).start();  
	        }  
	    }catch(java.io.IOException e)  
	    {  
	        e.printStackTrace();  
	    }  
	}  
	  
	 //���Handler��������IOSocket�̷߳�������Ϣ  
    android.os.Handler handler = new android.os.Handler()  
    {  
        public void handleMessage(android.os.Message msg)  
        {  
            if (msg.what == 0x1)  
            {  
                if (currentId > 2)  
                {  
                    currentId = 0;  
                }  
                if (currentId == 0)  
                {  
                    currentId++;  
                    //Intent intent = new Intent(server.this,FirstActivity.class);  
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                    //startActivity(intent);  
                }  
                else if (currentId == 1)  
                {  
                    currentId++;  
                    //Intent intent = new Intent(server.this,SecondActivity.class);  
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                    //startActivity(intent);  
                }  
                else if (currentId == 2)  
                {  
                    currentId++;  
                    //Intent intent = new Intent(server.this,ServerActivity.class);  
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
                    //startActivity(intent);  
                }  
            }  
        }  
    };  
    
    public class IOSocket implements Runnable{  
        private android.os.Handler handler;  
        private java.net.Socket client;  
          
          
        public IOSocket(android.os.Handler handler, java.net.Socket client)  
        {  
            this.handler = handler;  
            this.client = client;  
        }  
          
        public void run()  
        {  
            //BufferedOutputStream out;  
            java.io.BufferedInputStream in;  
            try  
            {  
                  
                //out = new BufferedOutputStream(client.getOutputStream());  
                in = new java.io.BufferedInputStream(client.getInputStream());  
                //PC�˷���������  
                String order = "";  
                //����socket  
                ioThreadFlag = true;  
                while (ioThreadFlag)  
                {  
                    try  
                    {  
                        if (!client.isConnected())  
                        {  
                            break;  
                        }  
                          
                          
                        //��ȡPC���͹���������  
                        order = readCMDFromSocket(in);  
                          
                        /* ��������ֱ������� */  
                        if (order.equals("switch"))   
                        {  
                        	android.os.Message msg = new android.os.Message();  
                            msg.what = 0x1;  
                            handler.sendMessage(msg);  
                            //out.write("OK".getBytes());  
                            //out.flush();  
                        }   
                        else if (order.equals("otherOrder"))   
                        {  
                            //out.write("OK".getBytes());  
                            //out.flush();  
                        }   
                        else if (order.equals("exit"))   
                        {  
      
                        }  
                    }catch (Exception e)  
                    {  
                        e.printStackTrace();  
                    }  
                }  
                //out.close();  
                in.close();  
            }catch(Exception e)  
            {  
                e.printStackTrace();  
            }finally  
            {  
                try  
                {  
                    if (client != null)  
                    {  
                        client.close();  
                    }  
                }catch(java.io.IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }  
        /* ��ȡ���� */  
        public String readCMDFromSocket(java.io.InputStream in)   
        {  
            int MAX_BUFFER_BYTES = 2048;  
            String msg = "";  
            byte[] tempbuffer = new byte[MAX_BUFFER_BYTES];  
            try   
            {  
                int numReadedBytes = in.read(tempbuffer, 0, tempbuffer.length);  
                msg = new String(tempbuffer, 0, numReadedBytes, "utf-8");  
                tempbuffer = null;  
            } catch (Exception e)   
            {  
                e.printStackTrace();  
            }  
            return msg;  
        }  
      
    }      
}
