/*************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/
package cs1501_lzw_lab;

public class LZW {
    private static int R = 256; // number of input chars
    private static int L = 512; // number of codewords = 2^W
    private static int W = 9; // codeword width

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1; // R is codeword for EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input); // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W); // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L) // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            if(code==L && W<16) {
                W++;
                L*=2;
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t); // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {
        L = 512;
        W = 9;
        String[] st = new String[65536];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = ""; // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R)
            return; // expanded message is empty string
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R)
                break;
            String s = st[codeword];
            if (i == codeword)
                s = val + val.charAt(0); // special case hack
            if (i < L)
                st[i++] = val + s.charAt(0);
            if(i==L && W<16) {
                W++;
                L*=2;
                st[i++] = val + s.charAt(0);
            }
            val = s;
        }
        BinaryStdOut.close();
    }
}
