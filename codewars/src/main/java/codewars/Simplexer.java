package codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

/**
 * @author boris.brinza
 */

class Token {
	private String text;
	private String type;

	Token(String text, String type) {
		this.text = text;
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Token token = (Token) o;

		if (text != null ? !text.equals(token.text) : token.text != null)
			return false;
		return type != null ? type.equals(token.type) : token.type == null;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Token{");
		sb.append("text='").append(text).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

public class Simplexer implements Iterator<Token> {
	private String str;
	int tokenizerPos = 0;

	public Simplexer(String buffer) {
		str = buffer;
		tokenizerPos = 0;
	}

	@Override
	public boolean hasNext() {
		return tokenizerPos < str.length();
	}

	@Override
	public Token next() {
		char c = str.charAt(tokenizerPos);
		if ("()+-*/%=".indexOf(c) != -1) {
			tokenizerPos++;
			return new Token(String.valueOf(c), "operator");
		} else if (Character.isDigit(c)) {
			StringBuilder num = new StringBuilder();
			while (tokenizerPos < str.length() && Character.isDigit(str.charAt(tokenizerPos))) {
				num = num.append(str.charAt(tokenizerPos));
				tokenizerPos++;
			}
			return new Token(num.toString(), "integer");
		} else if (Character.isWhitespace(c)) {
			while (Character.isWhitespace(str.charAt(tokenizerPos++)));
			return new Token(String.valueOf(c), "whitespace");
		} else if (c == '"') {
			StringBuilder s = new StringBuilder(c);
			tokenizerPos++;
			while (tokenizerPos < str.length() && str.charAt(tokenizerPos) != '"') {
				s = s.append(str.charAt(tokenizerPos++));
			}
			return new Token(s.toString(), "string");
		} else if (str.substring(tokenizerPos).equals("true")) {
			tokenizerPos += 4;
			return new Token("true", "boolean");
		} else if (str.substring(tokenizerPos).equals("false")) {
			tokenizerPos += 5;
			return new Token("false", "boolean");
		} else if (str.substring(tokenizerPos).equals("if")) {
			tokenizerPos += 2;
			return new Token("if", "keyword");
		} else if (str.substring(tokenizerPos).equals("else")) {
			tokenizerPos += 4;
			return new Token("else", "keyword");
		} else if (str.substring(tokenizerPos).equals("for")) {
			tokenizerPos += 3;
			return new Token("for", "keyword");
		} else if (str.substring(tokenizerPos).equals("while")) {
			tokenizerPos += 5;
			return new Token("while", "keyword");
		} else if (str.substring(tokenizerPos).equals("return")) {
			tokenizerPos += 6;
			return new Token("return", "keyword");
		} else if (str.substring(tokenizerPos).equals("func")) {
			tokenizerPos += 4;
			return new Token("func", "keyword");
		} else if (str.substring(tokenizerPos).equals("break")) {
			tokenizerPos += 5;
			return new Token("break", "keyword");
		} else {
			StringBuilder s = new StringBuilder();
			while (tokenizerPos < str.length() && (
					Character.isLetter(str.charAt(tokenizerPos)) || str.charAt(tokenizerPos) == '_')) {
				s.append(str.charAt(tokenizerPos++));
			}
			return new Token(s.toString(), "identifier");
		}

	}

	private static List<Token> toList(Simplexer lexer) {
		List<Token> tokens = new ArrayList<>();
		lexer.forEachRemaining(tokens::add);
		return tokens;
	}

	public static void main(String[] args) {
		Simplexer lexer = new Simplexer("");
		assertEquals(false, lexer.hasNext());

		lexer = new Simplexer("x");
		assertEquals(true, lexer.hasNext());
		assertEquals(new Token("x", "identifier"), lexer.next());

		lexer = new Simplexer("true");
		assertEquals(true, lexer.hasNext());
		assertEquals(new Token("true", "boolean"), lexer.next());

		lexer = new Simplexer("12345");
		assertEquals(true, lexer.hasNext());
		assertEquals(new Token("12345", "integer"), lexer.next());

		// String
		lexer = new Simplexer("\"Hello\"");
		assertEquals(true, lexer.hasNext());
		assertEquals(new Token("\"Hello\"", "string"), lexer.next());

		// Keyword
		lexer = new Simplexer("break");
		assertEquals(true, lexer.hasNext());
		assertEquals(new Token("break", "keyword"), lexer.next());

		lexer = new Simplexer("(1 + 2) - 5");
		assertEquals(Arrays.asList(new Token[] { new Token("(", "operator"), new Token("1", "integer"), new Token(" ", "whitespace"),
						new Token("+", "operator"), new Token(" ", "whitespace"), new Token("2", "integer"), new Token(")", "operator"),
						new Token(" ", "whitespace"), new Token("-", "operator"), new Token(" ", "whitespace"), new Token("5", "integer") }),
				toList(lexer));

		lexer = new Simplexer("return x + 1");
		assertEquals(Arrays.asList(new Token[] { new Token("return", "keyword"), new Token(" ", "whitespace"), new Token("x", "identifier"),
						new Token(" ", "whitespace"), new Token("+", "operator"), new Token(" ", "whitespace"), new Token("1", "integer") }),
				toList(lexer));
	}
}
