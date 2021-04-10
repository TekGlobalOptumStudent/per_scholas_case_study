package com.PatchworkNovels.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.UserService;

@Controller
public class SnippetController {

	@Autowired
	SnippetService snippetService;
	
	@Autowired
	UserService userService;

	@PostMapping("/uploadSnippet")
	public String uploadSnippet(HttpServletRequest request) {
		String snippetText = request.getParameter("snippetText");
		User user = userService.getUser(request.getParameter("snippetAuthor"));
		if (snippetText == null || snippetText.isBlank()) {
			if (request.getSession().getAttribute("editSnippet") != null)
				return "redirect:/profile/" + user.getUsername();
			request.getSession().setAttribute("message", "Snippet cannot be empty.");
			return "redirect:/createSnippet";
		}
		request.getSession().setAttribute("message", null);
		if (request.getSession().getAttribute("editSnippet") != null) {
			request.getSession().setAttribute("editSnippet", null);
			snippetService.editSnippet(Integer.parseInt(request.getParameter("snippetId")), snippetText);
			return "redirect:/profile/" + user.getUsername();
		}
		userService.addPublishedSnippet(user.getUsername(), new Snippet(user, snippetText));
		return "redirect:/profile/" + user.getUsername();
	}

	@PostMapping("/editSnippet")
	public ModelAndView editSnippet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createSnippet");
		Snippet snippet = snippetService.readSnippet(Integer.parseInt(request.getParameter("snippetId")));
		mav.addObject("snippetText", snippet.getSnippetText());
		mav.addObject("snippetId", snippet.getSnippetId());
		request.getSession().setAttribute("editSnippet", "true");
		return mav;
	}

	@RequestMapping("/deleteSnippet")
	public String deleteSnippet(HttpServletRequest request) {
		User user = userService.getUser(request.getParameter("username"));
		Snippet snippet = snippetService.readSnippet(Integer.parseInt(request.getParameter("snippetId")));
		userService.deletePublishedSnippet(user.getUsername(), snippet);
		return "redirect:/profile/" + user.getUsername();
	}
	
}
