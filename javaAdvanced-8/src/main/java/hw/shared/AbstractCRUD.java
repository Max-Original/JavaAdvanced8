package hw.shared;

import java.util.List;

public interface AbstractCRUD<T> {

	T creat(T t);

	T read(Integer id);

	T update(T t);

	void delete(Integer id);
	
	List<T> readAll();
}
