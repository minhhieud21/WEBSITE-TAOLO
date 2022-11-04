package com.example.JavaSpring.service;

public interface EmailService {

    boolean sendSimpleMail(String recipient, String msgBody,String link_website);
}
