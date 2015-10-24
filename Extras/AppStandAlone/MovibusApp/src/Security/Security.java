package Security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Security {

	/**
	 * Metodo que hace un cifrado simetrico delos bytes de entrada.
	 * @param msg El mensaje a encriptar.
	 * @param key La llave usada para encriptar.
	 * @param algo El algoritmo a encriptar.
	 * @return Los bytes cifrados que devolvio el algoritmo.
	 * @throws IllegalBlockSizeException Si hubo un error con el tama��o de la llave.
	 * @throws BadPaddingException Si hubo un error con el algoritmo.
	 * @throws InvalidKeyException Si la llave no es valida.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 * @throws NoSuchPaddingException Si el padding no es valido.
	 */
	public static byte[] symmetricEncryption (byte[] msg, Key key)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, 
			NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher decifrador = Cipher.getInstance("RC4"); 
		decifrador.init(Cipher.ENCRYPT_MODE, key); 
		return decifrador.doFinal(msg);
	}
	
	/**
	 * Metodo que hace un descifrado simetrico de los bytes de entrada.
	 * @param msg El mensaje a desencriptar.
	 * @param key La llave usada para encriptar.
	 * @param algo El algoritmo a encriptar.
	 * @return Los bytes cifrados que devolvio el algoritmo.
	 * @throws IllegalBlockSizeException Si hubo un error con el tama��o de la llave.
	 * @throws BadPaddingException Si hubo un error con el algoritmo.
	 * @throws InvalidKeyException Si la llave no es valida.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 * @throws NoSuchPaddingException Si el padding no es valido.
	 */
	public static byte[] symmetricDecryption (byte[] msg, Key key)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, 
			NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher decifrador = Cipher.getInstance("RC4"); 
		decifrador.init(Cipher.DECRYPT_MODE, key); 
		return decifrador.doFinal(msg);
	}
	
	/**
	 * Metodo que hace un cifrado asimetrico de los bytes de entrada.
	 * @param msg El mensaje a encriptar.
	 * @param key La llave usada para encriptar.
	 * @param algo El algoritmo a encriptar.
	 * @return Los bytes cifrados que devolvio el algoritmo.
	 * @throws IllegalBlockSizeException Si hubo un error con el tama��o de la llave.
	 * @throws BadPaddingException Si hubo un error con el algoritmo.
	 * @throws InvalidKeyException Si la llave no es valida.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 * @throws NoSuchPaddingException Si el padding no es valido.
	 */
	public static byte[] asymmetricEncryption (byte[] msg, Key key , String algo) 
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, 
			NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher decifrador = Cipher.getInstance(algo); 
		decifrador.init(Cipher.ENCRYPT_MODE, key); 
		return decifrador.doFinal(msg);
	}
	
	/**
	 * Metodo que hace un descifrado simetrico de los bytes de entrada.
	 * @param msg El mensaje a desencriptar.
	 * @param key La llave usada para encriptar.
	 * @param algo El algoritmo a encriptar.
	 * @return Los bytes cifrados que devolvio el algoritmo.
	 * @throws IllegalBlockSizeException Si hubo un error con el tama��o de la llave.
	 * @throws BadPaddingException Si hubo un error con el algoritmo.
	 * @throws InvalidKeyException Si la llave no es valida.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 * @throws NoSuchPaddingException Si el padding no es valido.
	 */
	public static byte[] asymmetricDecryption (byte[] msg, Key key , String algo) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
			IllegalBlockSizeException, BadPaddingException {
		Cipher decifrador = Cipher.getInstance(algo); 
		decifrador.init(Cipher.DECRYPT_MODE, key); 
		return decifrador.doFinal(msg);
	}
	
	/**
	 * Metodo que genera un codigo HMAC a partir de una llave, un mensaje y un algoritmo.
	 * @param msg El mensaje sobre el cual se va a aplicar el digest.
	 * @param key La llave que se usa para encriptar.
	 * @param algo El algoritmo que se va a utilizar para la encripcion.
	 * @return El digests en un arreglo de bytes.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 * @throws InvalidKeyException Si la llave no es valida.
	 * @throws IllegalStateException Si no fue posible hacer el digest.
	 * @throws UnsupportedEncodingException Si la codificacion no es valida.
	 */
	public static byte[] hmacDigest(byte[] msg, Key key, String algo) throws NoSuchAlgorithmException,
	InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		Mac mac = Mac.getInstance(algo);
		mac.init(key);

		byte[] bytes = mac.doFinal(msg);
		return bytes;
	}

	/**
	 * Metodo que verifica que un codigo HMAC corresponda con un mensaje dado.
	 * @param msg El mensaje que se quiere comprobar.
	 * @param key La llave simetrica con la cual se encripto.
	 * @param algo El algoritmo de generacion de HMAC.
	 * @param hash El hash que acompa��a al mensaje.
	 * @return La verificacion de que el mensaje y el codigo hmac coincidan.
	 * @throws Exception Si hubo un error al generar un mensaje HMAC.
	 */
	public static boolean verificarIntegridad(byte[] msg, Key key, String algo, byte [] hash ) throws Exception
	{
		byte [] nuevo = hmacDigest(msg, key, algo);
		if (nuevo.length != hash.length) {
			return false;
		}
		for (int i = 0; i < nuevo.length ; i++) {
			if (nuevo[i] != hash[i]) return false;
		}
		return true;
	}

	/**
	 * Metodo que se encarga de generar la llave simetrica de cualquier algoritmo.
	 * @param algoritmo - El algoritmo con el que va a encriptar la llave
	 * @return La llave simetrica.
	 * @throws NoSuchProviderException Si no hay un proveedor de seguridad.
	 * @throws NoSuchAlgorithmException Si el algoritmo no es valido.
	 */
	public static SecretKey keyGenGenerator() 
			throws NoSuchAlgorithmException, NoSuchProviderException	{
		int tamLlave = 128;
		
		KeyGenerator keyGen;
		SecretKey key;
		keyGen = KeyGenerator.getInstance("RC4","BC");
		keyGen.init(tamLlave);
		key = keyGen.generateKey();
		return key;
	}
}
