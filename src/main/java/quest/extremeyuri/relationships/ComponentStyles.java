package quest.extremeyuri.relationships;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ComponentStyles {
    public static TextColor weddingRingRelated = TextColor.color(0xffb508);
    public static TextColor loveRelated = TextColor.color(0xff4d71);
    public static TextColor divRelated = TextColor.color(0xBC0002);

    public static Style relationshipItemDesc = Style.style().color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC,false).build();
    public static Style weddingRingStyle = Style.style().color(weddingRingRelated).decoration(TextDecoration.ITALIC,false).build();
    public static Style loveLetterStyle = Style.style().color(loveRelated).decoration(TextDecoration.ITALIC,false).build();
    public static Style divorceStyle = Style.style().color(divRelated).decoration(TextDecoration.ITALIC,false).build();

}
