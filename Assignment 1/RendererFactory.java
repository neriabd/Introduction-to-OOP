/**
 * The RendererFactor class is responsible for creating instances of
 * various types of Renderers
 * @author Neriya Ben David
 * @see Renderer
 * @see ConsoleRenderer
 * @see VoidRenderer
 */
public class RendererFactory {

    // constants for renderer types
    private static final String CONSOLE_RENDERER = "console";
    private static final String VOID_RENDERER = "none";

    /**
     * Constructs a new RendererFactory object.
     */
    public RendererFactory() {
    }

    /**
     * Builds a new renderer according to the type given
     * @param type the type of renderer to build
     * @param size the size of the renderer
     * @return a new renderer object based on the type given
     */
    public Renderer buildRenderer(String type, int size) {
        // switch case to return the appropriate renderer based on the type given
        switch (type) {
            case CONSOLE_RENDERER:
                return new ConsoleRenderer(size);
            case VOID_RENDERER:
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
