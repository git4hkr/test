package com.example.demo.mybatis;

public interface CodableEnum {

	public abstract int getCode();

	public abstract CodableEnum fromCode(int code) throws IllegalArgumentException;

}