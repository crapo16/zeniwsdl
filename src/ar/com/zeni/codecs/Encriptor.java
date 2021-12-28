package ar.com.zeni.codecs;

public class Encriptor {

    public static void main(String[] args) {
        try {
            if (args.length < 2 || args.length > 3) {
                throw new RuntimeException("Los parametros son invalidos!");
            }
            if (args[0].equals("-shaBase64")) {
                System.out.print(GenericCodec.stringToBase64(GenericCodec.digestSHA1(args[1])));
            } else if (args[0].equals("-base64")) {
                System.out.print(GenericCodec.stringToBase64(args[1]));
            } else if (args[0].equals("-DESBase64")) {
                final String saltBase64 = GenericCodec.stringToBase64((args[2]));
                String passwordSHA1;
                passwordSHA1 = GenericCodec.digestSHA1((args[1]));
                final String passwordSHA1YBase64 = GenericCodec.stringToBase64(passwordSHA1);
                final byte[] bsalt = GenericCodec.base64ToByte(saltBase64);
                final byte[] bcodigodigeridoporDES = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, passwordSHA1YBase64, bsalt, null);
                final String codigodigeridoporDESenBase64 = GenericCodec.byteToBase64(bcodigodigeridoporDES);
                System.out.print(codigodigeridoporDESenBase64);
            } else {
                throw new RuntimeException("Los parametros son invalidos!");
            }
            System.out.print("\n");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        printHelp();
        System.exit(1);
    }

    private static void printHelp() {
        System.out.println("Error - Los modo de uso.");
        System.out.println(" Encriptor -shaBase64 string");
        System.out.println("   Donde string es una cadena que se quira encriptar con SHA-1 y expresada en base64.");
        System.out.println(" Encriptor -base64 string");
        System.out.println("   Donde string es una cadena que se quiere expresar en base64.");
        System.out.println(" Encriptor -DESBase64 string1 string2");
        System.out.println("   Donde string1 es el una cadena que se quiere encriptar con SHA-1, luego con DES utilizando string2 como salt y 1000 como repeticiones. ");
    }
}
