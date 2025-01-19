public class LZW {
    private static final int R = 256;        // number of input chars
    private static final int MAX_W = 16;     // maximum codeword width
    private static final int MAX_L = 1 << MAX_W; // maximum number of codewords

    public static void compress() {
        int W = 9;                          // initial codeword width
        int L = 1 << W;                     // initial number of codewords

        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1;  // R is codeword for EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < MAX_L) { // Add s to symbol table.
                if (code == L && W < MAX_W) {        // Increase codeword width
                    W++;
                    L = 1 << W;
                }
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);             // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {
        int W = 9;                          // initial codeword width
        int L = 1 << W;                     // initial number of codewords

        String[] st = new String[MAX_L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R)
            return;                          // expanded message is empty string
        String val = st[codeword];

        while (true) {
            if (i == L && W < MAX_W) {       // Increase codeword width
                W++;
                L = 1 << W;
            }
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R)
                break;
            String s;
            if (codeword < i)
                s = st[codeword];
            else if (codeword == i)
                s = val + val.charAt(0);
            else
                throw new IllegalArgumentException("Bad codeword");
            if (i < MAX_L)
                st[i++] = val + s.charAt(0);
            val = s;
        }
        BinaryStdOut.close();
    }
}
