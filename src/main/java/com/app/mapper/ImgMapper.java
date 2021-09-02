package com.app.mapper;

public class ImgMapper {
	
	private String imageText;

    private String imageId;

    private String imageLabel;

    private String imageUrl;

    private String imageTag;

    private String imageLastModified;

    public String getImageText ()
    {
        return imageText;
    }

    public void setImageText (String imageText)
    {
        this.imageText = imageText;
    }

    public String getImageId ()
    {
        return imageId;
    }

    public void setImageId (String imageId)
    {
        this.imageId = imageId;
    }

    public String getImageLabel ()
    {
        return imageLabel;
    }

    public void setImageLabel (String imageLabel)
    {
        this.imageLabel = imageLabel;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageTag ()
    {
        return imageTag;
    }

    public void setImageTag (String imageTag)
    {
        this.imageTag = imageTag;
    }

    public String getImageLastModified ()
    {
        return imageLastModified;
    }

    public void setImageLastModified (String imageLastModified)
    {
        this.imageLastModified = imageLastModified;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [imageText = "+imageText+", imageId = "+imageId+", imageLabel = "+imageLabel+", imageUrl = "+imageUrl+", imageTag = "+imageTag+", imageLastModified = "+imageLastModified+"]";
    }
}
			
