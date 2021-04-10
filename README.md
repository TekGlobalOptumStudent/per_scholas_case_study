# Patchwork Novels
---
## About
Welcome to Patchwork Novels, a website where you can make stories the fun way! 

This project was born from the idea of mixing and matching user contributions to create a single collage that anyone can see. It was inspired by a game called "One Word at a Time", where people would take turns creating an entire story only by saying one word a time. In this case, I expanded that idea to incorporate full sentences - even paragraphs - to create a more refined story.

## Tech Stack
This project features a front-end designed using Bootstrap and operates on a Spring MVC framework provided by Spring Boot. The website also communicates to a back-end database through MariaDB to fetch and store data to render on the front-end.

### Frameworks and Tech Used
- Bootstrap
- Spring Boot
- JUnit
- Hibernate
- MariaDB

### Languages used
<table>
	<tr>
		<th>Front-End</th>
		<th>Back-End</th>
	</tr>
	<tr>
		<td>HTML</td>
		<td>Java</td>
	</tr>
	<tr>
		<td>Javascript</td>
		<td>SQL</td>
	</tr>
	<tr>
		<td>CSS</td>
		<td></td>
	</tr>
</table>

## Main Features
The main idea of this website is to create full-fledged stories not by writing them out a sentence at a time, but by putting together "snippets" written by other user, much like patchwork!
<br><br>
When a user signs up, they are able to either contribute a snippet to be sent to the collection, or create a story using those snippets. In the story creation interface, a list representing all the snippets available in the collection appears on the right, with a board on the right that will hold the makeup of the story to be "written". Once a user is satisfied with their story, they may title it and submit it for others to see.
<br><br>
These stories then can show up at the home page as a popular or recently written story for others to read if they are interested. Clicking on any story or snippet will also take you to their respective pages:

- Story pages allow you to read the collection of snippets a whole, contiguous story, while also viewing the author who composed it. 
- Snippet pages allow you to see which stories feature that snippet, while again also viewing the author who wrote it.

Both views allow you to tag a story or snippet with comments, so you can let the author know how you feel about their contribution.
<br><br>
Lastly, each user has their own unique profile page that shows you their published stories and snippets, along with a favorites list for stories they find the best.