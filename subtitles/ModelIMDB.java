/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package subtitles;

class ModelIMDB {

  /**
   * The IMDB database key for this movie.
   **/
  private String _key;

  /**
   * The title.
   **/
  private String _title;

  /**
   * The constructor.
   **/
  protected ModelIMDB(String key, String title) {
    _key = key;
    _title = title;
  }

  /**
   * Gets the key.
   **/
  protected String getKey() {
    return _key;
  }

  /**
   * Gets the title.
   **/
  protected String getTitle() {
    return _title;
  }

  /**
   * Returns the title.
   **/
  public String toString() {
    return _title;
  }

}
