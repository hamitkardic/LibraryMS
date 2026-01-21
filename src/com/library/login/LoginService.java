package com.library.login;

import java.util.Scanner;

public class LoginService {
    Scanner sc = new Scanner(System.in);

   public void Login(){
       System.out.println("Please enter your username");
       String username = sc.nextLine();

       System.out.println("Please enter your password");
       String password = sc.nextLine();

   }
}
