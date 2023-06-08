public class service {
  public boolean textIsNumber(String str) {
    if (str == null || str == "") {
      str = "A";
    }
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        System.out.println("Has non-numeric in text: " + c);
        return false;
      }
    }
    return true;
  }
}
