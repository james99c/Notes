public class Note {

    /**
     * Stores the title of a note
     */
    private String title;
    /**
     * Stores the body of a note
     */
    private String body;

    /**
     *
     * Constructor for a new note
     *
     * @param _title The title of the note
     * @param _body The body of the note
     */
    Note(String _title, String _body) {
        this.title = _title;
        this.body = _body;
    }

    /**
     *
     * Get the title of a note
     *
     * @return A String containing the title of the note
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * Sets the title of a note
     *
     * @param _title The new title of the note
     */
    public void setTitle(String _title) {
        this.title = _title;
    }

    /**
     *
     * Get the body of a note
     *
     * @return A String containing the body of the note
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * Sets the body of a note
     *
     * @param _body The new body of the note
     */
    public void setBody(String _body) {
        this.body = _body;
    }

    @Override
    public String toString() {
        return ("-" + this.getTitle() + "\n    " + this.getBody());
    }
}
