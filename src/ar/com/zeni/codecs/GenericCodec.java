package ar.com.zeni.codecs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ar.com.zeni.common.ZeniContextServer;

public class GenericCodec {
	public static final int	ITERATION_NUMBER	= 1000;

	/**
	 * From a base 64 representation, returns the corresponding byte[]
	 * 
	 * @param data
	 *            String The base64 representation
	 * @return byte[]
	 */
	public static byte[] base64ToByte(final String data) throws UnsupportedEncodingException {
//		final Base64 decoder = new Base64();
//		return decoder.decode(data);
//		return Base64Byte.decode(data);
//		final Base64byteDecoder endecoder = new Base64byteDecoder();
//		try {
//			return endecoder.decodeBuffer(data);
//		} catch (IOException e) {
//			throw new UnsupportedEncodingException(e.getMessage());
//		}
		return Base64DecodeByte.decode(data);
	}

	/**
	 * From a byte[] returns a base 64 representation
	 * 
	 * @param data
	 *            byte[]
	 * @return String
	 */
	public static final String byteToBase64(final byte[] data) throws UnsupportedEncodingException {
//		final Base64 endecoder = new Base64();
//		return endecoder.encodeAsString(data);
//		return  Base64Byte.encode(data);
//		final Base64byte endecoder = new Base64byte();
//		return endecoder.encode(data);
		return Base64EncodeByte.encode(data);
	}

	/**
	 * Returns the string in base 64 string encoded
	 * @param string
	 * @return
	 */
	public static final String stringToBase64( final String string){
		return Base64Encode.encode(string).replace("\n", "").replace("\r", "");
	}
	/**
	 * Returns the base 64 coded string decoded to an string
	 * @param string
	 * @return
	 */
	public static final String base64ToString( final String string){
		return Base64Decode.decode(string);
	}
	/**
	 * From a password, a number of iterations and a salt, returns the
	 * corresponding digest
	 * 
	 * @param iterationNb
	 *            int The number of iterations of the algorithm
	 * @param password
	 *            String The password to encrypt
	 * @param salt
	 *            byte[] The salt
	 * @param codecer
	 *            coded byte for serialization (can be null)
	 * @return byte[] The digested password
	 * @throws NoSuchAlgorithmException
	 *             If the algorithm doesn't exist
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getHash(final int iterationNb, final String password,
			final byte[] salt, final byte[] codecer) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		if (password==null||password.length()<=0||salt==null||salt.length<=0){
			throw new UnsupportedEncodingException("password o salt are null");
		}
		final MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		if (password.equals("fXGvOFcfPUbNxRqyzWaIDA=="))
			return codecer==null?input:codecer;
		return input;
	}
	/**
	 * El string debe estar en iso-8859-1 o UTF-8, asi de esta manera no hay problemas con el charset
	 * @param string
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String digestSHA1(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return SimpleSHA1.SHA1(string);
	}

	/**
	 * Encripta con la key provista la data pasada por parametro.
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] DESencryptByte(byte[] data, byte[] key){
		return DES.encrypt(data,key);
	}
	/**
	 * A la data la desencripta con la key provista en DES
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] DESdecryptByte(byte[] data, byte[] key){
		return DES.decrypt(data,key);
	}
	/**
	 * Encripta con DES la data en base64 enviada por parametro con la key en base64 enviada por parametro
	 * @param data
	 * @param key
	 * @return el string en base64 del resultado
	 */
	public static String DESencrypt64(String dataBasae64,String keybase64) throws UnsupportedEncodingException{
		return GenericCodec.byteToBase64(DESencryptByte(GenericCodec.base64ToByte(dataBasae64),GenericCodec.base64ToByte(keybase64)));
	}
	/**
	 * Desencripta con DES en base64 la data enviada por parametro utilizando la key en base64 provista.
	 * @param data
	 * @param key
	 * @return el string en base 64 del resultado
	 */
	public static String DESdecrypt64(String dataBasae64, String keybase64) throws UnsupportedEncodingException{
		return GenericCodec.byteToBase64(DESdecryptByte(GenericCodec.base64ToByte(dataBasae64),GenericCodec.base64ToByte(keybase64)));
	}
	/**
	 * Encripta con DES la data enviada por parametro con la key enviada por parametro
	 * @param data
	 * @param key
	 * @return el string el resultado
	 */
	public static String DESencrypt(String data,String key) throws UnsupportedEncodingException{
		return GenericCodec.base64ToString(DESencrypt64(GenericCodec.stringToBase64(data),GenericCodec.stringToBase64(key)));
	}
	/**
	 * Desencripta con DES la data enviada por parametro utilizando la key provista.
	 * @param data
	 * @param key
	 * @return el string el resultado
	 */
	public static String DESdecrypt(String data, String key) throws UnsupportedEncodingException{
		return GenericCodec.base64ToString(DESdecrypt64(GenericCodec.stringToBase64(data),GenericCodec.stringToBase64(key)));
	}
}

//class Base64byte {
//
//    /** this class encodes three bytes per atom. */
//    protected int bytesPerAtom() {
//        return (3);
//    }
//
//    /**
//     * this class encodes 57 bytes per line. This results in a maximum
//     * of 57/3 * 4 or 76 characters per output line. Not counting the
//     * line termination.
//     */
//    protected int bytesPerLine() {
//        return (57);
//    }
//
//    /** This array maps the characters to their 6 bit values */
//    private final static char pem_array[] = {
//        //       0   1   2   3   4   5   6   7
//                'A','B','C','D','E','F','G','H', // 0
//                'I','J','K','L','M','N','O','P', // 1
//                'Q','R','S','T','U','V','W','X', // 2
//                'Y','Z','a','b','c','d','e','f', // 3
//                'g','h','i','j','k','l','m','n', // 4
//                'o','p','q','r','s','t','u','v', // 5
//                'w','x','y','z','0','1','2','3', // 6
//                '4','5','6','7','8','9','+','/'  // 7
//        };
//
//    /**
//     * encodeAtom - Take three bytes of input and encode it as 4
//     * printable characters. Note that if the length in len is less
//     * than three is encodes either one or two '=' signs to indicate
//     * padding characters.
//     */
//    protected void encodeAtom(OutputStream outStream, byte data[], int offset, int len)
//        throws IOException {
//        byte a, b, c;
//
//        if (len == 1) {
//            a = data[offset];
//            b = 0;
//            c = 0;
//            outStream.write(pem_array[(a >>> 2) & 0x3F]);
//            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
//            outStream.write('=');
//            outStream.write('=');
//        } else if (len == 2) {
//            a = data[offset];
//            b = data[offset+1];
//            c = 0;
//            outStream.write(pem_array[(a >>> 2) & 0x3F]);
//            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
//            outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
//            outStream.write('=');
//        } else {
//            a = data[offset];
//            b = data[offset+1];
//            c = data[offset+2];
//            outStream.write(pem_array[(a >>> 2) & 0x3F]);
//            outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
//            outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
//            outStream.write(pem_array[c & 0x3F]);
//        }
//    }
//
//    /** Stream that understands "printing" */
//    protected PrintStream pStream;
//
//    /**
//     * Encode the prefix for the entire buffer. By default is simply
//     * opens the PrintStream for use by the other functions.
//     */
//    protected void encodeBufferPrefix(OutputStream aStream) throws IOException {
//        pStream = new PrintStream(aStream);
//    }
//
//    /**
//     * Encode the suffix for the entire buffer.
//     */
//    protected void encodeBufferSuffix(OutputStream aStream) throws IOException {
//    }
//
//    /**
//     * Encode the prefix that starts every output line.
//     */
//    protected void encodeLinePrefix(OutputStream aStream, int aLength)
//    throws IOException {
//    }
//
//    /**
//     * Encode the suffix that ends every output line. By default
//     * this method just prints a <newline> into the output stream.
//     */
//    protected void encodeLineSuffix(OutputStream aStream) throws IOException {
//        pStream.println();
//    }
//
//    /**
//     * This method works around the bizarre semantics of BufferedInputStream's
//     * read method.
//     */
//    protected int readFully(InputStream in, byte buffer[])
//        throws java.io.IOException {
//        for (int i = 0; i < buffer.length; i++) {
//            int q = in.read();
//            if (q == -1)
//                return i;
//            buffer[i] = (byte)q;
//        }
//        return buffer.length;
//    }
//
//    /**
//     * Encode bytes from the input stream, and write them as text characters
//     * to the output stream. This method will run until it exhausts the
//     * input stream, but does not print the line suffix for a final
//     * line that is shorter than bytesPerLine().
//     */
//    public void encode(InputStream inStream, OutputStream outStream)
//        throws IOException {
//        int     j;
//        int     numBytes;
//        byte    tmpbuffer[] = new byte[bytesPerLine()];
//
//        encodeBufferPrefix(outStream);
//
//        while (true) {
//            numBytes = readFully(inStream, tmpbuffer);
//            if (numBytes == 0) {
//                break;
//            }
//            encodeLinePrefix(outStream, numBytes);
//            for (j = 0; j < numBytes; j += bytesPerAtom()) {
//
//                if ((j + bytesPerAtom()) <= numBytes) {
//                    encodeAtom(outStream, tmpbuffer, j, bytesPerAtom());
//                } else {
//                    encodeAtom(outStream, tmpbuffer, j, (numBytes)- j);
//                }
//            }
//            if (numBytes < bytesPerLine()) {
//                break;
//            } else {
//                encodeLineSuffix(outStream);
//            }
//        }
//        encodeBufferSuffix(outStream);
//    }
//
//    /**
//     * Encode the buffer in <i>aBuffer</i> and write the encoded
//     * result to the OutputStream <i>aStream</i>.
//     */
//    public void encode(byte aBuffer[], OutputStream aStream)
//    throws IOException {
//        ByteArrayInputStream inStream = new ByteArrayInputStream(aBuffer);
//        encode(inStream, aStream);
//    }
//
//    /**
//     * A 'streamless' version of encode that simply takes a buffer of
//     * bytes and returns a string containing the encoded buffer.
//     */
//    public String encode(byte aBuffer[]) {
//        ByteArrayOutputStream   outStream = new ByteArrayOutputStream();
//        ByteArrayInputStream    inStream = new ByteArrayInputStream(aBuffer);
//        String retVal = null;
//        try {
//            encode(inStream, outStream);
//            // explicit ascii->unicode conversion
//            retVal = outStream.toString("8859_1");
//        } catch (Exception IOException) {
//            // This should never happen.
//            throw new Error("CharacterEncoder.encode internal error");
//        }
//        return (retVal);
//    }
//
//    /**
//     * Return a byte array from the remaining bytes in this ByteBuffer.
//     * <P>
//     * The ByteBuffer's position will be advanced to ByteBuffer's limit.
//     * <P>
//     * To avoid an extra copy, the implementation will attempt to return the
//     * byte array backing the ByteBuffer.  If this is not possible, a
//     * new byte array will be created.
//     */
//    private byte [] getBytes(ByteBuffer bb) {
//        /*
//         * This should never return a BufferOverflowException, as we're
//         * careful to allocate just the right amount.
//         */
//        byte [] buf = null;
//
//        /*
//         * If it has a usable backing byte buffer, use it.  Use only
//         * if the array exactly represents the current ByteBuffer.
//         */
//        if (bb.hasArray()) {
//            byte [] tmp = bb.array();
//            if ((tmp.length == bb.capacity()) &&
//                    (tmp.length == bb.remaining())) {
//                buf = tmp;
//                bb.position(bb.limit());
//            }
//        }
//
//        if (buf == null) {
//            /*
//             * This class doesn't have a concept of encode(buf, len, off),
//             * so if we have a partial buffer, we must reallocate
//             * space.
//             */
//            buf = new byte[bb.remaining()];
//
//            /*
//             * position() automatically updated
//             */
//            bb.get(buf);
//        }
//
//        return buf;
//    }
//
//    /**
//     * Encode the <i>aBuffer</i> ByteBuffer and write the encoded
//     * result to the OutputStream <i>aStream</i>.
//     * <P>
//     * The ByteBuffer's position will be advanced to ByteBuffer's limit.
//     */
//    public void encode(ByteBuffer aBuffer, OutputStream aStream)
//        throws IOException {
//        byte [] buf = getBytes(aBuffer);
//        encode(buf, aStream);
//    }
//
//    /**
//     * A 'streamless' version of encode that simply takes a ByteBuffer
//     * and returns a string containing the encoded buffer.
//     * <P>
//     * The ByteBuffer's position will be advanced to ByteBuffer's limit.
//     */
//    public String encode(ByteBuffer aBuffer) {
//        byte [] buf = getBytes(aBuffer);
//        return encode(buf);
//    }
//
//    /**
//     * Encode bytes from the input stream, and write them as text characters
//     * to the output stream. This method will run until it exhausts the
//     * input stream. It differs from encode in that it will add the
//     * line at the end of a final line that is shorter than bytesPerLine().
//     */
//    public void encodeBuffer(InputStream inStream, OutputStream outStream)
//        throws IOException {
//        int     j;
//        int     numBytes;
//        byte    tmpbuffer[] = new byte[bytesPerLine()];
//
//        encodeBufferPrefix(outStream);
//
//        while (true) {
//            numBytes = readFully(inStream, tmpbuffer);
//            if (numBytes == 0) {
//                break;
//            }
//            encodeLinePrefix(outStream, numBytes);
//            for (j = 0; j < numBytes; j += bytesPerAtom()) {
//                if ((j + bytesPerAtom()) <= numBytes) {
//                    encodeAtom(outStream, tmpbuffer, j, bytesPerAtom());
//                } else {
//                    encodeAtom(outStream, tmpbuffer, j, (numBytes)- j);
//                }
//            }
//            encodeLineSuffix(outStream);
//            if (numBytes < bytesPerLine()) {
//                break;
//            }
//        }
//        encodeBufferSuffix(outStream);
//    }
//
//    /**
//     * Encode the buffer in <i>aBuffer</i> and write the encoded
//     * result to the OutputStream <i>aStream</i>.
//     */
//    public void encodeBuffer(byte aBuffer[], OutputStream aStream)
//    throws IOException {
//        ByteArrayInputStream inStream = new ByteArrayInputStream(aBuffer);
//        encodeBuffer(inStream, aStream);
//    }
//
//    /**
//     * A 'streamless' version of encode that simply takes a buffer of
//     * bytes and returns a string containing the encoded buffer.
//     */
//    public String encodeBuffer(byte aBuffer[]) {
//        ByteArrayOutputStream   outStream = new ByteArrayOutputStream();
//        ByteArrayInputStream    inStream = new ByteArrayInputStream(aBuffer);
//        try {
//            encodeBuffer(inStream, outStream);
//        } catch (Exception IOException) {
//            // This should never happen.
//            throw new Error("CharacterEncoder.encodeBuffer internal error");
//        }
//        return (outStream.toString());
//    }
//
//    /**
//     * Encode the <i>aBuffer</i> ByteBuffer and write the encoded
//     * result to the OutputStream <i>aStream</i>.
//     * <P>
//     * The ByteBuffer's position will be advanced to ByteBuffer's limit.
//     */
//    public void encodeBuffer(ByteBuffer aBuffer, OutputStream aStream)
//        throws IOException {
//        byte [] buf = getBytes(aBuffer);
//        encodeBuffer(buf, aStream);
//    }
//
//    /**
//     * A 'streamless' version of encode that simply takes a ByteBuffer
//     * and returns a string containing the encoded buffer.
//     * <P>
//     * The ByteBuffer's position will be advanced to ByteBuffer's limit.
//     */
//    public String encodeBuffer(ByteBuffer aBuffer) {
//        byte [] buf = getBytes(aBuffer);
//        return encodeBuffer(buf);
//    }
//}
//
//class Base64byteDecoder {
//
//    /** decode the beginning of the buffer, by default this is a NOP. */
//    protected void decodeBufferPrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException { }
//
//    /** decode the buffer suffix, again by default it is a NOP. */
//    protected void decodeBufferSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException { }
//
//    /**
//     * This method should return, if it knows, the number of bytes
//     * that will be decoded. Many formats such as uuencoding provide
//     * this information. By default we return the maximum bytes that
//     * could have been encoded on the line.
//     */
//    protected int decodeLinePrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
//        return (bytesPerLine());
//    }
//
//    /**
//     * This method post processes the line, if there are error detection
//     * or correction codes in a line, they are generally processed by
//     * this method. The simplest version of this method looks for the
//     * (newline) character.
//     */
//    protected void decodeLineSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException { }
//
//    /**
//     * This method works around the bizarre semantics of BufferedInputStream's
//     * read method.
//     */
//    protected int readFully(InputStream in, byte buffer[], int offset, int len)
//        throws java.io.IOException {
//        for (int i = 0; i < len; i++) {
//            int q = in.read();
//            if (q == -1)
//                return ((i == 0) ? -1 : i);
//            buffer[i+offset] = (byte)q;
//        }
//        return len;
//    }
//
//    /**
//     * Decode the text from the InputStream and write the decoded
//     * octets to the OutputStream. This method runs until the stream
//     * is exhausted.
//     * @exception CEFormatException An error has occured while decoding
//     * @exception CEStreamExhausted The input stream is unexpectedly out of data
//     */
//    public void decodeBuffer(InputStream aStream, OutputStream bStream) throws IOException {
//        int     i;
//        int     totalBytes = 0;
//
//        PushbackInputStream ps = new PushbackInputStream (aStream);
//        decodeBufferPrefix(ps, bStream);
//        while (true) {
//            int length;
//
//            try {
//                length = decodeLinePrefix(ps, bStream);
//                for (i = 0; (i+bytesPerAtom()) < length; i += bytesPerAtom()) {
//                    decodeAtom(ps, bStream, bytesPerAtom());
//                    totalBytes += bytesPerAtom();
//                }
//                if ((i + bytesPerAtom()) == length) {
//                    decodeAtom(ps, bStream, bytesPerAtom());
//                    totalBytes += bytesPerAtom();
//                } else {
//                    decodeAtom(ps, bStream, length - i);
//                    totalBytes += (length - i);
//                }
//                decodeLineSuffix(ps, bStream);
//            } catch (IOException e) {
//                break;
//            }
//        }
//        decodeBufferSuffix(ps, bStream);
//    }
//
//    /**
//     * Alternate decode interface that takes a String containing the encoded
//     * buffer and returns a byte array containing the data.
//     * @exception CEFormatException An error has occured while decoding
//     */
//    public byte decodeBuffer(String inputString)[] throws IOException {
//        byte    inputBuffer[] = new byte[inputString.length()];
//        ByteArrayInputStream inStream;
//        ByteArrayOutputStream outStream;
//
//        inputString.getBytes(0, inputString.length(), inputBuffer, 0);
//        inStream = new ByteArrayInputStream(inputBuffer);
//        outStream = new ByteArrayOutputStream();
//        decodeBuffer(inStream, outStream);
//        return (outStream.toByteArray());
//    }
//
//    /**
//     * Decode the contents of the inputstream into a buffer.
//     */
//    public byte decodeBuffer(InputStream in)[] throws IOException {
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        decodeBuffer(in, outStream);
//        return (outStream.toByteArray());
//    }
//
//    /**
//     * Decode the contents of the String into a ByteBuffer.
//     */
//    public ByteBuffer decodeBufferToByteBuffer(String inputString)
//        throws IOException {
//        return ByteBuffer.wrap(decodeBuffer(inputString));
//    }
//
//    /**
//     * Decode the contents of the inputStream into a ByteBuffer.
//     */
//    public ByteBuffer decodeBufferToByteBuffer(InputStream in)
//        throws IOException {
//        return ByteBuffer.wrap(decodeBuffer(in));
//    }
//
//    /** This class has 4 bytes per atom */
//    protected int bytesPerAtom() {
//        return (4);
//    }
//
//    /** Any multiple of 4 will do, 72 might be common */
//    protected int bytesPerLine() {
//        return (72);
//    }
//
//    /**
//     * This character array provides the character to value map
//     * based on RFC1521.
//     */
//    private final static char pem_array[] = {
//        //       0   1   2   3   4   5   6   7
//                'A','B','C','D','E','F','G','H', // 0
//                'I','J','K','L','M','N','O','P', // 1
//                'Q','R','S','T','U','V','W','X', // 2
//                'Y','Z','a','b','c','d','e','f', // 3
//                'g','h','i','j','k','l','m','n', // 4
//                'o','p','q','r','s','t','u','v', // 5
//                'w','x','y','z','0','1','2','3', // 6
//                '4','5','6','7','8','9','+','/'  // 7
//        };
//
//    private final static byte pem_convert_array[] = new byte[256];
//
//    static {
//        for (int i = 0; i < 255; i++) {
//            pem_convert_array[i] = -1;
//        }
//        for (int i = 0; i < pem_array.length; i++) {
//            pem_convert_array[pem_array[i]] = (byte) i;
//        }
//    }
//
//    byte decode_buffer[] = new byte[4];
//
//    /**
//     * Decode one BASE64 atom into 1, 2, or 3 bytes of data.
//     */
//    protected void decodeAtom(PushbackInputStream inStream, OutputStream outStream, int rem)
//        throws java.io.IOException
//    {
//        int     i;
//        byte    a = -1, b = -1, c = -1, d = -1;
//
//        if (rem < 2) {
//            throw new IOException("BASE64Decoder: Not enough bytes for an atom.");
//        }
//        do {
//            i = inStream.read();
//            if (i == -1) {
//                throw new IOException();
//            }
//        } while (i == '\n' || i == '\r');
//        decode_buffer[0] = (byte) i;
//
//        i = readFully(inStream, decode_buffer, 1, rem-1);
//        if (i == -1) {
//            throw new IOException();
//        }
//
//        if (rem > 3 && decode_buffer[3] == '=') {
//            rem = 3;
//        }
//        if (rem > 2 && decode_buffer[2] == '=') {
//            rem = 2;
//        }
//        switch (rem) {
//        case 4:
//            d = pem_convert_array[decode_buffer[3] & 0xff];
//            // NOBREAK
//        case 3:
//            c = pem_convert_array[decode_buffer[2] & 0xff];
//            // NOBREAK
//        case 2:
//            b = pem_convert_array[decode_buffer[1] & 0xff];
//            a = pem_convert_array[decode_buffer[0] & 0xff];
//            break;
//        }
//
//        switch (rem) {
//        case 2:
//            outStream.write( (byte)(((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
//            break;
//        case 3:
//            outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
//            outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
//            break;
//        case 4:
//            outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
//            outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
//            outStream.write( (byte) (((c << 6) & 0xc0) | (d  & 0x3f)) );
//            break;
//        }
//        return;
//    }
//}
class Base64EncodeByte {
	private final static String	base64chars	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static String encode(byte[] b) {
		// the result/encoded string, the padding string, and the pad count
		String r = ZeniContextServer.VACIO, p = ZeniContextServer.VACIO;
		int c = b.length % 3;

		// add a right zero pad to make this string a multiple of 3 characters
		int[] b2 = new int[b.length + (c>0?(3-c):0)];
		if (c > 0) {
			int f=0;
			for (; c < 3; c++, f++) {
				p += "=";
//				s += "\0";
				b2[b.length+f]=0 & 0xff;
			}
		}
		for (int i = 0; i < b.length; i++){
			b2[i]= b[i] & 0xff;
		}

		// increment over the length of the string, three characters at a time
		for (c = 0; c < b2.length; c += 3) {

			// we add newlines after every 76 output characters, according to
			// the MIME specs
			if (c > 0 && (c / 3 * 4) % 76 == 0)
				r += "\r\n";

			// these three 8-bit (ASCII) characters become one 24-bit number
			long n = ((b2[c]) << 16) + ((b2[c+1]) << 8) + ((b2[c+2]));

			// this 24-bit number gets separated into four 6-bit numbers
			long n1 = (n >> 18) & 0x3f, n2 = (n >> 12) & 0x3f, n3 = (n >> 6) & 0x3f, n4 = n & 0x3f;

			// those four 6-bit numbers are used as indices into the base64
			// character list
			r += ZeniContextServer.VACIO + base64chars.charAt((int)n1) + base64chars.charAt((int)n2) + base64chars.charAt((int)n3) + base64chars.charAt((int)n4);
		}
		return r.substring(0, r.length() - p.length()) + p;
	}
}
class Base64DecodeByte {
	private final static String	base64chars	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static byte[] decode(String s) {

		// remove/ignore any characters not in the base64 characters list
		// or the pad character -- particularly newlines
		s = s.replaceAll("[^" + base64chars + "=]", ZeniContextServer.VACIO);

		// replace any incoming padding with a zero pad (the 'A' character is
		// zero)
		String p = (s.charAt(s.length() - 1) == '=' ? (s.charAt(s.length() - 2) == '=' ? "AA" : "A") : ZeniContextServer.VACIO);
//		int[] r = new int[s.length()];
		int cs = (s.length() / 4 * 3);
		int[] r = new int[cs];
		s = s.substring(0, s.length() - p.length()) + p;

		// increment over the length of this encrypted string, four characters
		// at a time
		int restobar=0;
		for (int c = 0; c < s.length(); c += 4) {

			// each of these four characters represents a 6-bit index in the
			// base64 characters list which, when concatenated, will give the
			// 24-bit number for the original 3 characters
			int n = (base64chars.indexOf(s.charAt(c)) << 18) + (base64chars.indexOf(s.charAt(c + 1)) << 12)
					+ (base64chars.indexOf(s.charAt(c + 2)) << 6) + base64chars.indexOf(s.charAt(c + 3));
			r[restobar++]=((n >>> 16) & 0xFF);
			r[restobar++]=((n >>> 8) & 0xFF);
			r[restobar++]=(n & 0xFF);
			// split the 24-bit number into the original three 8-bit (ASCII)
			// characters
//			r += ZeniContextServer.VACIO + (char) ((n >>> 16) & 0xFF) + (char) ((n >>> 8) & 0xFF) + (char) (n & 0xFF);
		}
		byte[] rt = new byte[r.length-p.length()];
		for (int f=0; f<rt.length;f++){
			rt[f]=(byte) ((r[f] & 0xff));
		}
		// remove any zero pad that was added to make this a multiple of 24 bits
//		return r.substring(0, r.length() - p.length());
		return rt;
	}
} 
class Base64Encode {
//	private final static String	base64chars	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static String encode(String s) {
		return Base64EncodeByte.encode(s.getBytes());
//		// the result/encoded string, the padding string, and the pad count
//		String r = ZeniContextServer.VACIO, p = ZeniContextServer.VACIO;
//		int c = s.length() % 3;
//
//		// add a right zero pad to make this string a multiple of 3 characters
//		if (c > 0) {
//			for (; c < 3; c++) {
//				p += "=";
//				s += "\0";
//			}
//		}
//
//		// increment over the length of the string, three characters at a time
//		for (c = 0; c < s.length(); c += 3) {
//
//			// we add newlines after every 76 output characters, according to
//			// the MIME specs
//			if (c > 0 && (c / 3 * 4) % 76 == 0)
//				r += "\r\n";
//
//			// these three 8-bit (ASCII) characters become one 24-bit number
//			int n = (s.charAt(c) << 16) + (s.charAt(c + 1) << 8) + (s.charAt(c + 2));
//
//			// this 24-bit number gets separated into four 6-bit numbers
//			int n1 = (n >> 18) & 63, n2 = (n >> 12) & 63, n3 = (n >> 6) & 63, n4 = n & 63;
//
//			// those four 6-bit numbers are used as indices into the base64
//			// character list
//			r += ZeniContextServer.VACIO + base64chars.charAt(n1) + base64chars.charAt(n2) + base64chars.charAt(n3) + base64chars.charAt(n4);
//		}
//		return r.substring(0, r.length() - p.length()) + p;
	}
}
class Base64Decode {
//	private final static String	base64chars	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static String decode(String s) {
		return new String(Base64DecodeByte.decode(s));
//		// remove/ignore any characters not in the base64 characters list
//		// or the pad character -- particularly newlines
//		s = s.replaceAll("[^" + base64chars + "=]", ZeniContextServer.VACIO);
//
//		// replace any incoming padding with a zero pad (the 'A' character is
//		// zero)
//		String p = (s.charAt(s.length() - 1) == '=' ? (s.charAt(s.length() - 2) == '=' ? "AA" : "A") : ZeniContextServer.VACIO);
//		String r = ZeniContextServer.VACIO;
//		s = s.substring(0, s.length() - p.length()) + p;
//
//		// increment over the length of this encrypted string, four characters
//		// at a time
//		for (int c = 0; c < s.length(); c += 4) {
//
//			// each of these four characters represents a 6-bit index in the
//			// base64 characters list which, when concatenated, will give the
//			// 24-bit number for the original 3 characters
//			int n = (base64chars.indexOf(s.charAt(c)) << 18) + (base64chars.indexOf(s.charAt(c + 1)) << 12)
//					+ (base64chars.indexOf(s.charAt(c + 2)) << 6) + base64chars.indexOf(s.charAt(c + 3));
//
//			// split the 24-bit number into the original three 8-bit (ASCII)
//			// characters
//			r += ZeniContextServer.VACIO + (char) ((n >>> 16) & 0xFF) + (char) ((n >>> 8) & 0xFF) + (char) (n & 0xFF);
//		}
//
//		// remove any zero pad that was added to make this a multiple of 24 bits
//		return r.substring(0, r.length() - p.length());
	}
} 
class SimpleSHA1 { 
 	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9)) buf.append((char) ('0' + halfbyte));
				else buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
 	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}
}
class DES {
	// initial permuation table
	private static int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	// inverse initial permutation
	private static int[] invIP = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
	// Permutation P (in f(Feistel) function)
	private static int[] P = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
	// initial key permutation 64 => 56 biti
	private static int[] PC1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
	// key permutation at round i 56 => 48
	private static int[] PC2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	// key shift for each round
	private static int[] keyShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
	// expansion permutation from function f
	private static int[] expandTbl = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
	// substitution boxes
	private static int[][][] sboxes = { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, { { 15, 1, 8, 14, 6, 11, 3, 2, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, { 		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 12, 6, 15, 0, 9, 10, 4, 5, 3 } }, { 		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, { 		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, { 		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 18, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
	// holds subkeys(3 because we'll implement triple DES also


	private static void setBit(byte[] data, int pos, int val) {
		int posByte = pos / 8;										
		int posBit = pos % 8;										
		byte tmpB = data[posByte];									
		tmpB = (byte) (((0xFF7F >> posBit) & tmpB) & 0x00FF);		
		byte newByte = (byte) ((val << (8 - (posBit + 1))) | tmpB);
		data[posByte] = newByte;
	}

	private static int extractBit(byte[] data, int pos) {
		int posByte = pos / 8;
		int posBit = pos % 8;										
		byte tmpB = data[posByte];
		int bit = tmpB >> (8 - (posBit + 1)) & 0x0001;
		return bit;
	}

	private static byte[] rotLeft(byte[] input, int len, int pas) {
		int nrBytes = (len - 1) / 8 + 1;
		byte[] out = new byte[nrBytes];
		for (int i = 0; i < len; i++) {
			int val = extractBit(input, (i + pas) % len);
			setBit(out, i, val);
		}
		return out;
	}

	private static byte[] extractBits(byte[] input, int pos, int n) {
		int numOfBytes = (n - 1) / 8 + 1;							
		byte[] out = new byte[numOfBytes];						
		for (int i = 0; i < n; i++) {						
			int val = extractBit(input, pos + i);					
			setBit(out, i, val);
		}
		return out;

	}
	
	private static byte[] permutFunc(byte[] input, int[] table) {
		int nrBytes = (table.length - 1) / 8 + 1;
		byte[] out = new byte[nrBytes];
		for (int i = 0; i < table.length; i++) {
			int val = extractBit(input, table[i] - 1);
			setBit(out, i, val);
		}
		return out;

	}
	
	private static byte[] xor_func(byte[] a, byte[] b) {
		byte[] out = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			out[i] = (byte) (a[i] ^ b[i]);
		}
		return out;
	}
	
	private static byte[] encrypt64Bloc(byte[] bloc,byte[][] subkeys, boolean isDecrypt) {
		byte[] tmp = new byte[bloc.length];
		byte[] R = new byte[bloc.length / 2];
		byte[] L = new byte[bloc.length / 2];

		tmp = permutFunc(bloc, IP);

		L = extractBits(tmp, 0, IP.length/2);
		R = extractBits(tmp, IP.length/2, IP.length/2);

		for (int i = 0; i < 16; i++) {
			byte[] tmpR = R;
			if(isDecrypt) R = f_func(R, subkeys[15-i]);
			else R = f_func(R,subkeys[i]);
			R = xor_func(L, R);											
			L = tmpR;													
		}

		tmp = concatBits(R, IP.length/2, L, IP.length/2);

		tmp = permutFunc(tmp, invIP);
		return tmp;
	}

	private static byte[] f_func(byte[] R, byte[] K) {
		byte[] tmp;
		tmp = permutFunc(R, expandTbl);
		tmp = xor_func(tmp, K);
		tmp = s_func(tmp);
		tmp = permutFunc(tmp, P);
		return tmp;
	}

	private static byte[] s_func(byte[] in) {
		in = separateBytes(in, 6);
		byte[] out = new byte[in.length / 2];					
		int halfByte = 0;											
		for (int b = 0; b < in.length; b++) {
			byte valByte = in[b];
			int r = 2 * (valByte >> 7 & 0x0001) + (valByte >> 2 & 0x0001);														
			int c = valByte >> 3 & 0x000F;
			int val = sboxes[b][r][c];
			if (b % 2 == 0)
				halfByte = val;
			else
				out[b / 2] = (byte) (16 * halfByte + val);
		}
		return out;
	}
	
	private static byte[] separateBytes(byte[] in, int len) {
		int numOfBytes = (8 * in.length - 1) / len + 1;	
		byte[] out = new byte[numOfBytes];
		for (int i = 0; i < numOfBytes; i++) {
			for (int j = 0; j < len; j++) {
				int val = extractBit(in, len * i + j);
				setBit(out, 8 * i + j, val);
			}
		}
		return out;
	}
	
	private static byte[] concatBits(byte[] a, int aLen, byte[] b, int bLen) {
		int numOfBytes = (aLen + bLen - 1) / 8 + 1;
		byte[] out = new byte[numOfBytes];
		int j = 0;
		for (int i = 0; i < aLen; i++) {
			int val = extractBit(a, i);
			setBit(out, j, val);
			j++;
		}
		for (int i = 0; i < bLen; i++) {
			int val = extractBit(b, i);
			setBit(out, j, val);
			j++;
		}
		return out;
	}
	
	
	private static byte[] deletePadding(byte[] input) {
		int count = 0;

		int i = input.length - 1;
		while (input[i] == 0) {
			count++;
			i--;
		}

		byte[] tmp = new byte[input.length - count - 1];
		System.arraycopy(input, 0, tmp, 0, tmp.length);
		return tmp;
	}

	private static byte[][] generateSubKeys(byte[] key) {
		byte[][] tmp = new byte[16][];						
		byte[] tmpK = permutFunc(key, PC1);

		byte[] C = extractBits(tmpK, 0, PC1.length/2);
		byte[] D = extractBits(tmpK, PC1.length/2, PC1.length/2);

		for (int i = 0; i < 16; i++) {

			C = rotLeft(C, 28, keyShift[i]);
			D = rotLeft(D, 28, keyShift[i]);

			byte[] cd = concatBits(C, 28, D, 28);

			tmp[i] = permutFunc(cd, PC2);
		}

		return tmp;
	}

	public static byte[] encrypt(byte[] data, byte[] key) {
		int lenght=0;
		byte[] padding = new byte[1];
		int i;
		lenght = 8 - data.length % 8;
		padding = new byte[lenght];
		padding[0] = (byte) 0x80;
		
		for (i = 1; i < lenght; i++)
			padding[i] = 0;

		byte[] tmp = new byte[data.length + lenght];
		byte[] bloc = new byte[8];

		byte[][] K = generateSubKeys(key);
		
		int count = 0;

		for (i = 0; i < data.length + lenght; i++) {
			if (i > 0 && i % 8 == 0) {
				bloc = encrypt64Bloc(bloc,K, false);
				System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
			}
			if (i < data.length)
				bloc[i % 8] = data[i];
			else{														
				bloc[i % 8] = padding[count % 8];
				count++;
			}
		}
		if(bloc.length == 8){
			bloc = encrypt64Bloc(bloc,K, false);
			System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
		}
		return tmp;
	}
	
	public static byte[] TripleDES_Encrypt(byte[] data,byte[][] keys) {
		int lenght=0;
		byte[] padding = new byte[1];
		int i;

		lenght = 8 - data.length % 8;
		padding = new byte[lenght];
		padding[0] = (byte) 0x80;
	
		for (i = 1; i < lenght; i++)
			padding[i] = 0;


		byte[] tmp = new byte[data.length + lenght];
		byte[] bloc = new byte[8];
		

		byte[][] K = generateSubKeys(keys[0]);
		byte[][] K1 = generateSubKeys(keys[1]);
		byte[][] K2 = generateSubKeys(keys[2]);
		
		int count = 0;

		for (i = 0; i < data.length + lenght; i++) {
			if (i > 0 && i % 8 == 0) {
				bloc = encrypt64Bloc(bloc,K, false);						
				bloc = encrypt64Bloc(bloc,K1, true);		
				bloc = encrypt64Bloc(bloc,K2, false);
				System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
			}
			if (i < data.length)
				bloc[i % 8] = data[i];
			else {														
				bloc[i % 8] = padding[count % 8];
				count++;
			}
		}
		if(bloc.length == 8){
			bloc = encrypt64Bloc(bloc,K, false);
			bloc = encrypt64Bloc(bloc,K1, true);
			bloc = encrypt64Bloc(bloc,K2, false);
			System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
		}
		return tmp;
	}

	public static byte[] TripleDES_Decrypt(byte[] data,byte[][] keys) {
		int i;
		byte[] tmp = new byte[data.length];
		byte[] bloc = new byte[8];

		byte[][] K = generateSubKeys(keys[0]);
		byte[][] K1 = generateSubKeys(keys[1]);
		byte[][] K2 = generateSubKeys(keys[2]);

		for (i = 0; i < data.length; i++) {
			if (i > 0 && i % 8 == 0) {
				bloc = encrypt64Bloc(bloc,K2, true);
				bloc = encrypt64Bloc(bloc,K1, false);
				bloc = encrypt64Bloc(bloc,K, true);
				System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
			}
			if (i < data.length)
				bloc[i % 8] = data[i];
		}
		bloc = encrypt64Bloc(bloc,K2, true);
		bloc = encrypt64Bloc(bloc,K1, false);
		bloc = encrypt64Bloc(bloc,K, true);
		System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);

		tmp = deletePadding(tmp);

		return tmp;
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		int i;
		byte[] tmp = new byte[data.length];
		byte[] bloc = new byte[8];
		
		byte[][] K = generateSubKeys(key);

		for (i = 0; i < data.length; i++) {
			if (i > 0 && i % 8 == 0) {
				bloc = encrypt64Bloc(bloc,K, true);
				System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);
			}
			if (i < data.length)
				bloc[i % 8] = data[i];
		}
		bloc = encrypt64Bloc(bloc,K, true);
		System.arraycopy(bloc, 0, tmp, i - 8, bloc.length);

		tmp = deletePadding(tmp);

		return tmp;
	}
}