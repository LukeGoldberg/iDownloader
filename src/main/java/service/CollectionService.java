package service;

/**
 * 
 *
 */
public interface CollectionService {
	
	/**
	 * 
	 * @param dto
	 */
	<T> void collect(T dto);

}
