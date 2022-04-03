package io.szrharrison.mkveditor;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageListener implements ApplicationListener<JavaFxApplication.StageReadyEvent> {

  private final String applicationTitle;

  public StageListener(@Value("${spring.application.ui.title}") String applicationTitle) {
    this.applicationTitle = applicationTitle;
  }

  @Override
  public void onApplicationEvent(JavaFxApplication.StageReadyEvent stageReadyEvent) {
    Stage stage = stageReadyEvent.getStage();
    Scene scene = new Scene(stageReadyEvent.getRoot(), 1067, 600);
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    stage.setScene(scene);
    stage.setTitle(this.applicationTitle);
    stage.show();
  }
}