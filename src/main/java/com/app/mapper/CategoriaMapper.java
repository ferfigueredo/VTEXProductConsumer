package com.app.mapper;

public class CategoriaMapper {
	
	    private String MetaTagDescription;

	    private CategoriaMapper[] children;

	    private boolean hasChildren;

	    private String name;

	    private String Title;

	    private String id;

	    private String url;

	    public String getMetaTagDescription ()
	    {
	        return MetaTagDescription;
	    }

	    public void setMetaTagDescription (String MetaTagDescription)
	    {
	        this.MetaTagDescription = MetaTagDescription;
	    }

	    public CategoriaMapper[] getChildren ()
	    {
	        return children;
	    }

	    public void setChildren (CategoriaMapper[] children)
	    {
	        this.children = children;
	    }

	    public boolean getHasChildren ()
	    {
	        return hasChildren;
	    }

	    public void setHasChildren (boolean hasChildren)
	    {
	        this.hasChildren = hasChildren;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getTitle ()
	    {
	        return Title;
	    }

	    public void setTitle (String Title)
	    {
	        this.Title = Title;
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public String getUrl ()
	    {
	        return url;
	    }

	    public void setUrl (String url)
	    {
	        this.url = url;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [MetaTagDescription = "+MetaTagDescription+", children = "+children+", hasChildren = "+hasChildren+", name = "+name+", Title = "+Title+", id = "+id+", url = "+url+"]";
	    }
	}
				
