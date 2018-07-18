
package main.helper;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * A web service endpoint interface.
 * 
 * @author www.codejava.net
 */
@WebService
public interface FileTransferer
{
	@WebMethod
	public String upload(String filePath, String fileName, String imageBase64);

	@WebMethod
	public String download(String filePath);

	@WebMethod
	public void delete(String filePath);
}
