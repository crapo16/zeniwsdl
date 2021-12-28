package ar.com.zeni.mail;

import java.util.ArrayList;

public interface Mail {
	public abstract ArrayList<String> getTo();

	public abstract String getFrom();

	public abstract String getSubject();

	public abstract String getBody();
}