package _283095.javafx;

import java.util.ArrayList;
import java.util.List;

public class Attivita
{

  private String name;
  private List<Persona> iscritti;

  public Attivita(String _name, List<Persona> _iscritti)
  {
    name = _name;
    iscritti = new ArrayList<Persona>(_iscritti);
  }

  public Attivita(String _name)
  {
    name = _name;
    iscritti = new ArrayList<>();
  }

  public String getName()
  {
    return this.name;
  }

  public List<Persona> getIscritti()
  {
    return this.iscritti;
  }

}
