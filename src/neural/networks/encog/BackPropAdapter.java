package neural.networks.encog;

import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

import neural.TrainMethodAdapter;
import neural.parsec.ast.TrainingDef;

public class BackPropAdapter extends AbstractTrainingAdapter {

	@Override
	protected MLTrain getTrainer(TrainingDef definiton) {
		return new Backpropagation(getUnderLyingNetwork(), getData());
	}

}
