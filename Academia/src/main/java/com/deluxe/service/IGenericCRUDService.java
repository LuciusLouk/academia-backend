package com.deluxe.service;

import java.util.List;

public interface IGenericCRUDService<T> {
	public List<T> getAll();
	public T getByID(Long id);
	public T setObject(T o);
	public void modObject(T o);
	public void delObject(Long id);
}
