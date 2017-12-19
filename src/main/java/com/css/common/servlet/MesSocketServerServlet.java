package com.css.common.servlet;

import javax.servlet.http.HttpServlet;

public class MesSocketServerServlet extends HttpServlet{

	private static final long serialVersionUID = 1265234135634586932L;
	//private ServerSocket serverSocket ;

	
	@Override
	public void init(){
		ServerSo so = ServerSo.getInstance();
		so.start();
		
		//final WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//new MesApacheMqReaderHandler(wac);
		/*try {
			serverSocket = new ServerSocket(50001,50);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return; 
		}
		while(true){
			Socket cl = null ;
			try {
				cl = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(cl != null){
				MesSocketServerThread sst = new MesSocketServerThread(cl);
				sst.run();
			}
		}*/
	}
}
