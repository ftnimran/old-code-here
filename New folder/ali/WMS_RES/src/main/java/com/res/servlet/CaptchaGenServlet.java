package com.res.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.res.constants.RESConstants;
import com.res.util.RESUtil;

public class CaptchaGenServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public static final String FILE_TYPE = "jpeg";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletOutputStream out = null;
		Graphics2D graphics = null;
		try {
			String captchaStr=RESUtil.generateCaptchaText(4);

			HttpSession session = request.getSession(true);
			if("reset".equals(request.getParameter("type"))){
				session.setAttribute(RESConstants.CAPTCHA_RESET, captchaStr); // reset password page
			}else if("login".equals(request.getParameter("type"))){
				session.setAttribute(RESConstants.CAPTCHA_LOGIN, captchaStr); // login page
			}else if("signup".equals(request.getParameter("type"))){
				session.setAttribute(RESConstants.CAPTCHA_SIGNUP, captchaStr); // signup page
			}

			response.setContentType("image/jpg");
			out = response.getOutputStream();

			BufferedImage image = new BufferedImage(100, 35, BufferedImage.TYPE_INT_RGB);

			graphics = image.createGraphics();

			// Set back ground of the generated image to white
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, 100, 35);

			// set gradient font of text to be converted to image
			GradientPaint gradientPaint = new GradientPaint(0, 0, Color.DARK_GRAY, 20, 10, Color.LIGHT_GRAY, true);
			graphics.setPaint(gradientPaint);
			Font font = new Font("Comic Sans MS", Font.BOLD, 18);
			graphics.setFont(font);
			graphics.drawString(captchaStr, 10, 20);
			ImageIO.write(image, FILE_TYPE, out);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			// close the stream
			try {
				if(null!=out) {
					out.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
			if(null!=graphics) {
				// release resources used by graphics context
				graphics.dispose();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doPost(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
