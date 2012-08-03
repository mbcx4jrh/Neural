package neural.networks.encog;


import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import neural.TrainMethodAdapter;
import neural.parsec.ast.TrainingDef;


public class ResilientPropAdapter extends AbstractTrainingAdapter implements TrainMethodAdapter {

	@Override
	protected MLTrain getTrainer(TrainingDef definiton) {
		return new ResilientPropagation(getUnderLyingNetwork(), getData());
	}
	
	

}
