package com.record;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDAO;
import com.dao.PatientDAO;
import com.dao.PatientFileDAO;
import com.dto.DoctorDTO; 
import com.dto.PatientDTO;
import com.dto.PatientFileDTO;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBUtil;
import com.util.FileManager;
import com.util.MyUtil;

public class recordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			System.out.println("error - " + e.toString() + " : " + e.getMessage());
		}
		
		MyUtil myUtil = new MyUtil();
		
		String uri = req.getRequestURI();
		String url;
		
		String rootpath = getServletContext().getRealPath("/");
		
		String txtPath = rootpath + "recordVoice" + File.separator + "txt";
		String wavPath = rootpath + "recordVoice" + File.separator + "wav";
		File Folder1 = new File(txtPath);
		File Folder2 = new File(wavPath);
		
		String encType = "UTF-8";
		int maxSize = 10 * 1024 * 1024;
 
		if (!Folder1.exists()) {
			try{
				Folder1.mkdirs(); 
			    } catch(Exception e){
			    e.getStackTrace();
			    }        
		}
		
		if (!Folder2.exists()) {
			try{
				Folder2.mkdirs(); 
			    } catch(Exception e){
			    e.getStackTrace();
			    }        
		}

		System.out.println("doPost " + uri);
		
		if (uri.indexOf("home.do") != -1) {
			
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			System.out.println("id : " + id + ", pw : " + pw);
			
			HttpSession session = req.getSession();
			DoctorDAO dao = new DoctorDAO(conn);
			DoctorDTO dto = dao.getReadDataOne(id, pw);
			
			DoctorDTO getSessionDto = (DoctorDTO) session.getAttribute("DoctorDto");
			
			if(getSessionDto == null) { 
			
				if(dto.getId() == "" || dto.getId() == null) {
					
					req.setAttribute("check", "false");
					
					url = "/index.jsp";
					forward(req, resp, url);
					return;
				}
				
				session.setAttribute("DoctorDto", dto);
				
				req.setAttribute("dto", dto);
				
			} else { 
				session.setAttribute("DoctorDto", dto);
				req.setAttribute("dto", getSessionDto);
				
			}
			
			url = "/list_home.jsp";
			forward(req, resp, url);
			
		}  else if(uri.indexOf("list.do") != -1) {
			
			int page = Integer.parseInt(req.getParameter("page"));
			String name = req.getParameter("name");
			String birth = req.getParameter("birth");
			String gender = req.getParameter("gender");
			String cellphone = req.getParameter("cellphone");

			birth = birth.replace("-", "");

			System.out.println(name); 
			System.out.println(birth); 
			System.out.println(gender); 
			System.out.println(cellphone); 
			
			int currentPage = 1;
			int listViewCount = 10;
			int start = 1;
			int end = 10;
			
			if(page > 1) {
				currentPage = page;
				
				start = (currentPage - 1) * listViewCount + 1;
				end = currentPage * listViewCount;
			}
			
			PatientDAO dao = new PatientDAO(conn);
			
			int totalPage = myUtil.getPageCount(listViewCount, dao.getDataCount(name,birth,gender,cellphone));
			
			/*if(totalPage == 0)
				totalPage = 1;*/
			
			String patientPagingText = myUtil.getPatientPagingText(currentPage, totalPage);

			List<PatientDTO> lists = dao.getReadData(name,birth,gender,cellphone,start,end);
 
			if(totalPage != 0) {
				lists.get(0).setPatientPagingText(patientPagingText);
			}
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			String gson = new Gson().toJson(lists);
			
			try {
                resp.getWriter().write(gson);
            } catch (JsonIOException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
			
		} else if(uri.indexOf("patientInfoSave.do") != -1) {
			
			String pName = req.getParameter("pName");
			String pBirth = req.getParameter("pBirth");
			String pGender = req.getParameter("pGender");
			String pNum = req.getParameter("pNum");
			String drcodeval = req.getParameter("drcodeval");
			
			PatientDAO dao = new PatientDAO(conn);
			PatientDTO dto = new PatientDTO();
			
			String maxCode = dao.getMaxPatientCode();
			
			if(maxCode == null || maxCode == "") {
				maxCode = "A00000";
			} else {
				maxCode = "A" + String.format("%05d", (Integer.parseInt(maxCode.substring(1)) + 1));
			}
			
			HttpSession session = req.getSession();
			
			DoctorDTO doctorDto = (DoctorDTO) session.getAttribute("DoctorDto");
			System.out.println(drcodeval);
			System.out.println(pName);
			
			dto.setDoctorcode(drcodeval);
			dto.setName(pName);
			dto.setBirth(pBirth.replace("-",""));
			dto.setGender(pGender);
			dto.setCellphone(pNum);
			dto.setPatientcode(maxCode);
			
			dao.insertPatientData(dto);
			try {
                resp.getWriter().write("");
            } catch (JsonIOException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
			
			req.setAttribute("dto", dto);
			req.setAttribute("patientcode", maxCode);
			req.setAttribute("drcode", drcodeval);
			url = "/diagnosis_record_start.jsp";
			forward(req, resp, url);
			
		} else if(uri.indexOf("history.do") != -1) {
			
			String drcode = req.getParameter("drcode");
			String patientcode = req.getParameter("patientcode");
			
			System.out.println("drcode"+drcode);
			System.out.println("patientcode"+patientcode);
			
			HttpSession session = req.getSession();
			
			DoctorDTO dto = (DoctorDTO) session.getAttribute("DoctorDto");
			 
			req.setAttribute("dto", dto);
			req.setAttribute("drcode", drcode);
			req.setAttribute("patientcode", patientcode);
			
			url = "/list_history.jsp";
			forward(req, resp, url);
			
		} else if(uri.indexOf("historyList.do") != -1) {
			
			int page = Integer.parseInt(req.getParameter("page"));
			String drcode = req.getParameter("drcode");
			String patientcode = req.getParameter("patientcode");
			
			int currentPage = 1;
			int listViewCount = 8;
			int start = 1;
			int end = 8;
			
			if(page > 1) {
				currentPage = page;
				
				start = (currentPage - 1) * listViewCount + 1;
				end = currentPage * listViewCount;
			}
			
			PatientFileDAO dao = new PatientFileDAO(conn);
			
			int totalPage = myUtil.getPageCount(listViewCount, dao.getDataCount(patientcode));
			
			String patientPagingText = myUtil.getPatientPagingText(currentPage, totalPage);
			
			List<PatientFileDTO> lists = dao.getReadData(patientcode,start,end);
			
			if(totalPage != 0) {
				lists.get(0).setPatientPagingText(patientPagingText);
			}
			
			req.setAttribute("drcode", drcode);
			req.setAttribute("patientcode", patientcode);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			String gson = new Gson().toJson(lists);
			
			try {
                resp.getWriter().write(gson);
            } catch (JsonIOException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
			
		} else if(uri.indexOf("agree.do") != -1) {
			
			String patientcode = req.getParameter("patientcode");
			String drcode = req.getParameter("drcode");
			
			HttpSession session = req.getSession();
			
			DoctorDTO dto = (DoctorDTO) session.getAttribute("DoctorDto");
			
			req.setAttribute("dto", dto);
			req.setAttribute("patientcode", patientcode);
			req.setAttribute("drcode", drcode);
			
			url = "/private_agree.jsp";
			forward(req, resp, url);
			
		} else if(uri.indexOf("recordStart.do") != -1) {
			
			String patientcode = req.getParameter("patientcode");
			String drcode = req.getParameter("drcode");
			
			HttpSession session = req.getSession();
			
			DoctorDTO dto = (DoctorDTO) session.getAttribute("DoctorDto");
			
			req.setAttribute("dto", dto);
			req.setAttribute("patientcode", patientcode);
			req.setAttribute("drcode", drcode);
			
			url = "/diagnosis_record_start.jsp";
			forward(req, resp, url);
			
		} else if(uri.indexOf("saveFile.do") != -1) {
			
			MultipartRequest mr = new MultipartRequest(req, wavPath, maxSize,encType, new DefaultFileRenamePolicy());
			
			String patientcode = mr.getParameter("patientcode");
			String drcode = mr.getParameter("drcode");
			
			String content = mr.getParameter("content");
			
			if(content == null)
				content = "";
			
			/*String patientcode = req.getParameter("patientcode");
			String content = req.getParameter("content");*/
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
			
			String now = format1.format(System.currentTimeMillis());
			
			String wavFileName = now + "_" + patientcode + ".wav";
			String txtFileName = now + "_" + patientcode + ".txt";
			
			PatientFileDAO dao = new PatientFileDAO(conn);
			PatientFileDTO dto = new PatientFileDTO();
			
			File oldFile = new File(wavPath + File.separator + mr.getFilesystemName("file"));
			File newFile = new File(wavPath + File.separator + wavFileName);

			oldFile.renameTo(newFile);  
			
			int seq = dao.getMaxSeq(patientcode) + 1;
			
            BufferedWriter fw = new BufferedWriter(new FileWriter(txtPath + File.separator + txtFileName, true));
              
            fw.write(content);
            fw.flush();
  
            fw.close();
            
            HttpSession session = req.getSession();
            DoctorDTO doctorDto = (DoctorDTO) session.getAttribute("DoctorDto");
            
            dto.setPatientcode(patientcode);
            dto.setDoctorcode(drcode);
            dto.setWavfilename(wavFileName);
            dto.setTxtfilename(txtFileName);
            dto.setWavfileloc(("recordVoice" + File.separator + "wav").replace("\\", "/"));
            dto.setTxtfileloc(("recordVoice" + File.separator + "txt").replace("\\", "/"));
            dto.setSeq(seq+"");
            
            dao.insertPatientFileData(dto);
            
			try { 
                resp.getWriter().write("OK");
            } catch (IOException e) {
                e.printStackTrace();
            }
			
		} else if(uri.indexOf("deleteData.do") != -1) {
			
			String patientcode = req.getParameter("patientcode");
			String seq = req.getParameter("seq");
			
			PatientFileDAO dao = new PatientFileDAO(conn);
			dao.deleteData(patientcode,seq);
			
			try { 
                resp.getWriter().write("OK");
            } catch (IOException e) {
                e.printStackTrace();
            }
			
		} else if(uri.indexOf("logout.do") != -1) {
			
			HttpSession session = req.getSession();
			
			session.removeAttribute("DoctorDto");
			session.invalidate();
			
			System.out.println("session : " + session);
			
			url = "/stt";
			resp.sendRedirect(url);
			
		} else if(uri.indexOf("fileDownload.do") != -1) {
			
			String fileName = req.getParameter("fileName");
			String flagText = req.getParameter("flag");
			
			boolean flag = false;
			
			if(flagText.equals("wav")) {
				flag = FileManager.doFileDownload(resp, fileName, fileName, wavPath);
			}
			else {
				flag = FileManager.doFileDownload(resp, fileName, fileName, txtPath);
			}
			
			if(flag == false) {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.print("<script type='text/javascript'>");
				out.print("alert('download error');");
				out.print("history.back()");
				out.print("</script>");
			}
			
		} else {
			
			String check = req.getParameter("check");
			
			req.setAttribute("check", check);
			
			url = "/index.jsp";
			forward(req, resp, url);
			
		}
		
	}
	
}
