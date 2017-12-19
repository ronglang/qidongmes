package com.css.common.util.workflow.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * @author ������ E-mail:����kaioct@qq.com 
 * @version ����ʱ�䣺2014-11-3 ����02:47:49 
 * ��˵���� ����sax��ʽ����XML
 */
public class WorkflowProcessSaxService extends DefaultHandler{
	private List<Activity> activityList=null;
	private List<Transition> transitionList=null;
	
	private Transition trans=null;
	private Activity activity=null;
	private String preTag = null;//�����Ǽ�¼����ʱ����һ���ڵ����
	
	public List<Activity> getActivities(){
		return activityList;
	}
	
	public List<Transition> getTransitions(){
		return transitionList;
	}
	
	public static void main(String[] args) {
		try {
			WorkflowProcessSaxService saxService= new WorkflowProcessSaxService();
			//InputStream is= this.getClass().getClassLoader().getResourceAsStream("book.xml");
			//������Դ�ļ� ת��Ϊһ�������� book.xml��Ҫ����src��Ŀ¼��
            InputStream is=WorkflowProcessSaxService.class.getClassLoader().getResourceAsStream("com/xml/workflow/Ա�������������.xml");
            WorkflowProcess bean= saxService.getWorkflowProcessData(is);
            List<Activity> activityList=null;
        	List<Transition> transList=null;
            if(null!=bean){
            	activityList=bean.getActivityList();
            	transList=bean.getTransitionList();
            	System.out.println("Activitie info=====");
            	for(Activity act:activityList){
            		System.out.println("id:"+act.getId()+" type:"+act.getType()+" name:"+act.getName()+" xCoordinate:"+act.getXCoordinate()+" yCoordinate:"+act.getYCoordinate()+" width:"+act.getWidth()+" height:"+act.getHeight());
            	}
            	System.out.println("Transition info=====");
            	for(Transition trans:transList){
            		System.out.println("id:"+trans.getId()+" name:"+trans.getName()+" from:"+trans.getFrom()+" to:"+trans.getTo());
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WorkflowProcess getWorkflowProcessData(InputStream xmlStream) throws Exception{
		WorkflowProcess bean=null;
		List tmpList=null;
		List tmpList2=null;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		WorkflowProcessSaxService service= new WorkflowProcessSaxService();
		parser.parse(xmlStream,service);
		
		tmpList=service.getActivities();
		tmpList2=service.getTransitions();
		if(null!=tmpList && tmpList.size()>0 && null!=tmpList2 && tmpList2.size()>0){
			bean=new WorkflowProcess();
			bean.setActivityList(tmpList);
			bean.setTransitionList(tmpList2);
		}
		return bean;
	}
	
	@Override
	public void startDocument() throws SAXException {
		activityList = new ArrayList<Activity>();
		transitionList = new ArrayList<Transition>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("Activitie".equals(qName)){
			activity= new Activity();//<Activitie id="1" type="START_NODE" name="Ա�����" xCoordinate="22" yCoordinate="39" width="80" height="30"></Activitie>
			activity.setId(attributes.getValue(0));
			activity.setType(attributes.getValue(1));
			activity.setName(attributes.getValue(2));
			activity.setXCoordinate(attributes.getValue(3));
			activity.setYCoordinate(attributes.getValue(4));
			activity.setWidth(attributes.getValue(5));
			activity.setHeight(attributes.getValue(6));
		}
		if("Transition".equals(qName)){
			trans=new Transition();// <Transition id="8" name="��ٷ���" from="1" to="2"></Transition>
			trans.setId(attributes.getValue(0));//Ҳ����attributes.getValue("id"));
			trans.setName(attributes.getValue(1));
			trans.setFrom(attributes.getValue(2));
			trans.setTo(attributes.getValue(3));
		}
		preTag = qName;//�����ڽ����Ľڵ���Ƹ���preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("Activitie".equals(qName)){
			activityList.add(activity);
			activity= null;
		}
		if("Transition".equals(qName)){
			transitionList.add(trans);
			trans= null;
		}
		preTag = null;
		/**����������ʱ��Ϊ�ա��������Ҫ�����磬��ͼ�л�3��λ�ý���󣬻�����������
		��������ﲻ��preTag��Ϊnull�����startElement(....)������preTag��ֵ����book�����ĵ�˳�����ͼ
		�б��4��λ��ʱ����ִ��characters(char[] ch, int start, int length)�����������characters(....)��
		���ж�preTag!=null����ִ��if�жϵĴ��룬����ͻ�ѿ�ֵ��ֵ��book���ⲻ��������Ҫ�ġ�*/
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
//		if(preTag!=null){
//			String content = new String(ch,start,length);
//			if("name".equals(preTag)){
//				book.setName(content);
//			}else if("price".equals(preTag)){
//				book.setPrice(Float.parseFloat(content));
//			}
//		}
	}
	
	/**
     * SAX�����������������ĵ��ڵ�ʱ���õķ���
     */
    public void endDocument() throws SAXException {
        //System.out.println("endDocument()������������������");
    }
 
}
