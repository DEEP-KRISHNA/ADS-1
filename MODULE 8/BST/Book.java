/**
 * the following is Book data Structure which used in BST as key.
 *
 * @author Sandeep KOlli.
 */
class Book implements Comparable<Book> {
	/**
	 * Title of the book.
	 */
	String title;
	/**
	 * Author of the book.
	 */
	String author;
	/**
	 * Price of the book.
	 */
	double price;

	Book(String title, String author, double price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	/**
	 * title is the unique key in Book Data Structure.
	 *
	 * Based on title we compare 2 books.
	 *
	 * @param that with what book you wanna compare.
	 * @return value based on comparision.
	 */
	public int compareTo(final Book that) {
		return this.title.compareTo(that.title);
	}
}