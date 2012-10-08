package neural.networks.encog;

import neural.parsec.ast.TrainingDef;

import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.training.propagation.scg.ScaledConjugateGradient;

public class ScaledConjugateAdapter extends AbstractTrainingAdapter{

	@Override
	protected MLTrain getTrainer(TrainingDef definiton) {
		return new ScaledConjugateGradient(this.getUnderLyingNetwork(), this.getData());
	}


}
