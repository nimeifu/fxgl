import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

public class playerComponet extends Component {
    private PositionComponent position;

    private double speed =0;

    @Override
    public void onUpdate(double tpf)
    {
        speed =tpf*60;
    }

    public void up()
    {
        position.translateY(-2*speed);
    }

    public void down()
    {
        position.translateY(2*speed);
    }

    public void left(){
        position.translateX(-2*speed);
    }

    public void right(){
        position.translateX(2*speed);
    }
}
