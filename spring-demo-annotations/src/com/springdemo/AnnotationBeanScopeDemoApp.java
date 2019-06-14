package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp
{

	public static void main(String[] args)
	{
		// load spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// retrieve bean from spring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		// Coach alphaCoach = context.getBean("tennisCoach", Coach.class);

		// check if they are the same
		// boolean result = (theCoach == alphaCoach);
		// System.out.println("\nPointing to the same object: " + result);
		// System.out.println("\nMemory location for theCoach: " + theCoach);
		// System.out.println("\nMemory location for alphaCoach: " + alphaCoach);

		// using the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());

		// close
		context.close();
	}

}