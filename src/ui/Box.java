package ui;

public class Box {
    private String msg;
    private String[] separated;

    public Box() {
        this.msg = "";
    }

    /**
     * This is the function where the box is created, it concatenates a String with
     * the components of the box with the message inside it
     * 
     * @return This function returns the original message contained by the tokens
     *         selected by the user
     */

    // I used a single function because I didn't want to instance a box for each
    // different message
    public String box(String message, int margin, String tokenColumns, String tokenRows) {
        int acu = 0;

        separated = message.split("\n");

        int dupMargin = margin * 2;

        int maxStringLen = maxStringLen();

        int caps = maxStringLen + dupMargin;

        int columnCaps = caps * tokenRows.length();

        msg += line(caps, tokenRows);

        for (int i = 0; i < separated.length; i++) {
            border(separated[acu], columnCaps, tokenColumns);
            acu += 1;

            if (i != separated.length - 1) {
                msg += line(caps, tokenRows);
            }
        }

        msg += line(caps, tokenRows);

        return msg;
    }

    /**
     * This function creates a line with the token selected by the user
     * 
     * @param limit this is tended to be major String length plus the margin
     * 
     * @return A String line made of the tokens with the major String length plus
     *         the margin
     */
    public String line(int limit, String token) {
        return String.valueOf(token).repeat(Math.max(0, limit));
    }

    /**
     * This creates the borders for each word in the message
     * 
     * @param message    The message in which is going to be applied the border
     * @param totalLimit The max message's length in the separated array
     */
    public void border(String message, int totalLimit, String tokenColumns) {
        msg += "\n" + tokenColumns;
        int half;

        if (totalLimit == message.length()) {
            half = 1;
        } else {
            half = (totalLimit - message.length()) / 2;
        }

        for (int i = 0; i < half - 1; i++) {
            msg += " ";
        }

        for (int i = 0; i < message.length(); i++) {
            msg += message.charAt(i);
        }

        if (message.length() % 2 != 0 && message.length() != totalLimit) {
            half += 1;
        }

        for (int i = 0; i < half - 1; i++) {
            msg += " ";
        }

        msg += tokenColumns + "\n";
    }

    /**
     * This function searches the biggest String in the separated array
     * 
     * @return The biggest String's length in the separated array
     */
    public int maxStringLen() {
        int bigger = 0;

        for (String s : separated) {
            if (s.length() > bigger) {
                bigger = s.length();
            }
        }

        return bigger;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
