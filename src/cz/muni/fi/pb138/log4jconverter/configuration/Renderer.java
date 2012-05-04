package cz.muni.fi.pb138.log4jconverter.configuration;

/**
 *
 * @author Steve
 */
public class Renderer {
    //required
    private String renderedClass;
    private String renderingClass;
    
    public void setRenderedClass(String renderedClass) {
        this.renderedClass = renderedClass;
    }

    public void setRenderingClass(String renderingClass) {
        this.renderingClass = renderingClass;
    }

    public String getRenderedClass() {
        return renderedClass;
    }

    public String getRenderingClass() {
        return renderingClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Renderer other = (Renderer) obj;
        if ((this.renderedClass == null) ? (other.renderedClass != null) : !this.renderedClass.equals(other.renderedClass)) {
            return false;
        }
        if ((this.renderingClass == null) ? (other.renderingClass != null) : !this.renderingClass.equals(other.renderingClass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.renderedClass != null ? this.renderedClass.hashCode() : 0);
        hash = 67 * hash + (this.renderingClass != null ? this.renderingClass.hashCode() : 0);
        return hash;
    }
    
}
