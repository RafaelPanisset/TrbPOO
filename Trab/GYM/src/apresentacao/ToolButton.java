package apresentacao;

import javax.swing.*;

public class ToolButton extends JButton
{
	private static final long serialVersionUID = 1L;

	public ToolButton(Action a)
    {   super((Icon)a.getValue(Action.SMALL_ICON));
        String toolTip = (String)a.getValue(Action.SHORT_DESCRIPTION);
        if (toolTip == null)
        toolTip = (String)a.getValue(Action.NAME);
        if (toolTip != null)
        setToolTipText(toolTip);
        addActionListener(a);
    }
}