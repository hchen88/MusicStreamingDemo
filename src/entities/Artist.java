package entities;

public class Artist
{
    private String id;
    private String name;
    private String terms;

    public Artist(String id, String name, String terms)
    {
        this.id = id;
        this.name = name;
        this.terms = terms;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTerms()
    {
        return terms;
    }

    public void setTerms(String terms)
    {
        this.terms = terms;
    }
}
