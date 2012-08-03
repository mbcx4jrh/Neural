package neural.networks.encog;

import neural.parsec.ast.TrainingDef;

import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

public class BackPropAdapter extends AbstractTrainingAdapter {

	@Override
	protected MLTrain getTrainer(TrainingDef definiton) {
		return new Backpropagation(getUnderLyingNetwork(), getData());
	}

}
