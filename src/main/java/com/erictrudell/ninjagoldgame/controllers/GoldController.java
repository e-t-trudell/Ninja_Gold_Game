package com.erictrudell.ninjagoldgame.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GoldController {
	@GetMapping("/")
	public String home(
			HttpSession session,
			Model model
			) {
		session.setAttribute("gold", 0);
		System.out.println("Gold: " + session.getAttribute("gold"));
		session.setAttribute("activitySession", null);
		model.addAttribute("activityModel", null);
		
	
		return "index.jsp";
	}
	@GetMapping("/gold")
	public String gold(HttpSession session) {
		if (session.getAttribute("gold") == null ||
				session.getAttribute("farm") == null ||
				session.getAttribute("cave") == null ||
				session.getAttribute("castle") == null ||
				session.getAttribute("quest") == null
				) {
			session.setAttribute("gold", 0);
			
			System.out.println("Gold: " + session.getAttribute("gold"));
		}
		return "index.jsp";
	}
	@PostMapping("/addgold")
	public String addGold(
			@RequestParam(value="farm", required=false) String farm,
			@RequestParam(value="cave", required=false) String cave,
			@RequestParam(value="castle", required=false) String castle,
			@RequestParam(value="quest", required=false) String quest,
			HttpSession session, 
			Model model
			) {
		
		session.setAttribute("farm", 30);
		session.setAttribute("cave", 40);
		session.setAttribute("castle", 20);
		session.setAttribute("quest", 100);
		Integer goldCount = (Integer) session.getAttribute("gold");
		Integer farmCount = (Integer) session.getAttribute("farm");
		Integer caveCount = (Integer) session.getAttribute("cave");
		Integer castleCount = (Integer) session.getAttribute("castle");
		Integer questCount = (Integer) session.getAttribute("quest");
		ArrayList<String> activities = new ArrayList<String>();
		session.setAttribute("activityList", activities);
		String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
		String date = simpleDate.format(new Date());
		Random rando = new Random();
		String farmMessage = String.format("You entered a farm and earned 30 gold. " + date);

//		START HERE YOUR LIST IS NOT BEING ADDED TO MODEL CORRECTLY. 
//		YOU CAN ALSO ONLY SEE LAST ADDITION TO SESSION
//		farm
		if (cave == null && castle == null && quest == null) {
			System.out.println("before farm increment " + goldCount);
			goldCount += farmCount;
			System.out.println("after farm increment: " + goldCount);
			session.setAttribute("gold", goldCount);
			System.out.println("session gold "+session.getAttribute("gold"));
//			activities.add(String.format("You entered a farm and earned 30 gold. " + date));
			activities.add(farmMessage);
			session.setAttribute("activities", activities);
			System.out.println("activities:" + activities.get(0));
			model.addAttribute("activitiesModel", activities);
			System.out.println("activityModel: "+model.getAttribute("activityModel"));
			
			session.setAttribute("activitySession", activities);
			System.out.println("activitySession" + session.getAttribute("activitySession"));
		}
//		cave
		else if(farm == null && castle == null && quest == null){
			System.out.println("before cave increment " + goldCount);
			goldCount += caveCount;
			System.out.println("after cave increment: " + goldCount);
			session.setAttribute("gold", goldCount);
			System.out.println("session gold "+session.getAttribute("gold"));
			activities.add(String.format("You entered a dragon cave and earned 40 gold. " + date));
			
			session.setAttribute("activityList", activities);
		}
//		castle
		else if(farm == null && cave == null && quest == null){	
			System.out.println("before castle increment " + goldCount);
			goldCount += castleCount;
			System.out.println("after castle increment: " + goldCount);
			session.setAttribute("gold", goldCount);
			System.out.println("session gold "+session.getAttribute("gold"));
			activities.add(String.format("You entered a castle and earned 20 gold. " + date));
			session.setAttribute("activityList", activities);
		}
//		quest
		else if(farm == null && cave == null && castle == null){
			if (rando.nextBoolean() == true) {
				System.out.println("before true quest increment " + goldCount);
				goldCount += questCount;
				System.out.println("after true quest increment: " + goldCount);
				session.setAttribute("gold", goldCount);
				System.out.println("session gold "+session.getAttribute("gold"));
				activities.add(String.format("You took a quest and earned 100 gold. " + date));
				session.setAttribute("activityList", activities);
			} else {
				session.setAttribute("quest", 50);
				System.out.println("before false quest increment " + goldCount);
				goldCount -= questCount;
				System.out.println("after false quest increment: " + goldCount);
				session.setAttribute("gold", goldCount);
				System.out.println("session gold "+session.getAttribute("gold"));
				activities.add(String.format("You took a quest and lost 50 gold. " + date));
				session.setAttribute("activityList", activities);
			}
		}
		else {
			System.out.println("You should not see this line!");
		}
		session.getAttribute("activitySession");
		model.getAttribute("activityModel");
		System.out.println("session activities" + session.getAttribute("activitySession"));
		System.out.println("model activityList: "+model.getAttribute("activityModel"));
	
	return "redirect:/gold";
	}
	@PostMapping("/remove")
	public String removeGold(HttpSession session) {
		session.setAttribute("gold", 0);
		String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
		String date = simpleDate.format(new Date());
		System.out.println(date);
		session.setAttribute("activities", String.format("You have lost all your gold! " + date));
		return "redirect:/gold";
	}
//	RedirectAttributes redirectAttributes
//	@PostMapping("/addfarm")
//	public String addFarm(@RequestParam(value = "farm") String farm,
//			HttpSession session, 
//			Model model
//			) {
//
//		if (session.getAttribute("gold") == null) {
//			session.setAttribute("gold", 20);
//			System.out.println("session Gold: " + session.getAttribute("gold"));
//		} else {
//			Integer goldCount = (Integer) session.getAttribute("gold");
//			session.setAttribute("farm", 20);
//			Integer farmCount = (Integer) session.getAttribute("farm");
//			System.out.println("before farm increment " + goldCount);
//			goldCount += farmCount;
//			System.out.println("after farm increment: " + goldCount);
//			session.setAttribute("gold", goldCount);
//			System.out.println("session gold "+session.getAttribute("gold"));
//
//			String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
//			SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
//			String date = simpleDate.format(new Date());
////			System.out.println(date);
//			
////			session.setAttribute("activities", String.format("You entered a farm and earned 20 gold. " + date));
//			
////			flash message will only persist one redirect
////			redirectAttributes.addFlashAttribute("farmActivity", String.format("You entered a farm and earned 20 gold. " + date));
//			session.getAttribute("activities");
//			ArrayList<String> activities = new ArrayList<String>();
//			activities.add(String.format("You entered a farm and earned 20 gold. " + date));
//			session.setAttribute("activities", activities);
//			System.out.println("session activities"+session.getAttribute("activities"));
//			
//			model.addAttribute("activityList", activities);
//			System.out.println("model activityList: "+model.getAttribute("activityList"));
//		}
//		return "redirect:/gold";
//	}

//	@PostMapping("/adddragon")
//	public String addDragon(@RequestParam(value = "cave") String dragonCave, HttpSession session) {
//		if (session.getAttribute("gold") == null) {
//			session.setAttribute("gold", 0);
//			System.out.println("Gold: " + session.getAttribute("gold"));
//		} else {
//			Integer goldCount = (Integer) session.getAttribute("gold");
//			session.setAttribute("cave", 40);
//			Integer cave = (Integer) session.getAttribute("cave");
//			System.out.println("before increment " + goldCount);
//			goldCount += cave;
//
//			System.out.println("after increment: " + goldCount);
//			session.setAttribute("gold", goldCount);
//			System.out.println(session.getAttribute("gold"));
//			System.out.println("count is: " + goldCount);
//
//			String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
//			SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
//			String date = simpleDate.format(new Date());
//			System.out.println(date);
//			session.setAttribute("activities", String.format("You entered a dragon cave and earned 40 gold. " + date));
//			System.out.println(session.getAttribute("activities"));
//
//		}
//		return "redirect:/gold";
//	}
//
//	@PostMapping("/addcastle")
//	public String addCastle(@RequestParam(value = "castle") String castle, HttpSession session) {
//		if (session.getAttribute("gold") == null) {
//			session.setAttribute("gold", 0);
//			System.out.println("Gold: " + session.getAttribute("gold"));
//		} else {
//			Integer goldCount = (Integer) session.getAttribute("gold");
//			session.setAttribute("castle", 20);
//			Integer castleCount = (Integer) session.getAttribute("castle");
////			Integer quest = (Integer) session.getAttribute("quest");
//			System.out.println("before increment " + goldCount);
//			goldCount += castleCount;
//
//			System.out.println("after increment: " + goldCount);
//			session.setAttribute("gold", goldCount);
//			System.out.println(session.getAttribute("gold"));
//			System.out.println("count is: " + goldCount);
//
//			String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
//			SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
//			String date = simpleDate.format(new Date());
//			System.out.println(date);
//			session.setAttribute("activities", String.format("You entered a dragon cave and earned 40 gold. " + date));
//			System.out.println(session.getAttribute("activities"));
//		}
//		return "redirect:/gold";
//	}
//
//	
////	START HERE your true adds correctly but the false does not subtract
////	You also need to add activities to session or to the model differently only last post is shown
//	@PostMapping("/quest")
//	public String quest(@RequestParam(value = "quest") String quest, HttpSession session) {
//		Random rando = new Random();
//		System.out.println(rando.nextBoolean());
//
//		String pattern = "MMMM-dd-yyyy H:m:ss zzzz";
//		SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
//		String date = simpleDate.format(new Date());
//		System.out.println(date);
//		if (rando.nextBoolean() == true) {
//			session.setAttribute("quest", 100);
//			System.out.println("Gold: " + session.getAttribute("quest"));
//			Integer goldCount = (Integer) session.getAttribute("gold");
//			System.out.println("before true increment " + goldCount);
//			Integer questCount = (Integer) session.getAttribute("quest");
//			goldCount += questCount;
//			System.out.println("after true increment: " + goldCount);
//			session.setAttribute("gold", goldCount);
//			session.setAttribute("activities", String.format("You embarked on a quest and earned 100 gold. " + date));
//			System.out.println(session.getAttribute("activities"));
//		} else {
//			session.setAttribute("quest", 50);
//			Integer goldCount = (Integer) session.getAttribute("gold");
//			System.out.println("before false increment " + goldCount);
//			Integer questCount = (Integer) session.getAttribute("quest");
//			goldCount -= questCount; 
//
//			System.out.println("after false increment: " + goldCount);
//			session.setAttribute("gold", goldCount);
//			System.out.println(session.getAttribute("gold"));
//			System.out.println("count is: " + goldCount);
//			session.setAttribute("activities", String.format("You embarked on a quest and lost 50 gold. " + date));
//			System.out.println(session.getAttribute("activities"));
//		}
//		return "redirect:/gold";
//	}

	

}
