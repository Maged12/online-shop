import { useReducer } from "react";

interface State {
  opened: boolean;
  isRegister: boolean;
  isCancelModal: boolean;
}

const initialState: State = {
  opened: false,
  isRegister: false,
  isCancelModal: false,
};

const actions = Object.freeze({
  OPEN_MODAL: "OPEN_MODAL" as const,
  CLOSE_MODAL: "CLOSE_MODAL" as const,
  OPEN_CANCEL_MODAL: "OPEN_CANCEL_MODAL" as const,
  CLOSE_CANCEL_MODAL: "CLOSE_CANCEL_MODAL" as const,
});

type Action =
  | { type: typeof actions.OPEN_MODAL; }
  | { type: typeof actions.CLOSE_MODAL; }
  | { type: typeof actions.OPEN_CANCEL_MODAL; }
  | { type: typeof actions.CLOSE_CANCEL_MODAL; };

const reducer = (state: State, action: Action): State => {
  switch (action.type) {
    case actions.OPEN_MODAL:
      return { ...state, opened: true };
    case actions.CLOSE_MODAL:
      return { ...state, opened: false };
    case actions.OPEN_CANCEL_MODAL:
      return { ...state, isCancelModal: true };
    case actions.CLOSE_CANCEL_MODAL:
      return { ...state, isCancelModal: false };
    default:
      return state;
  }
};

const useModal = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const openModal = (isRegister = true) => {
    state.isRegister = isRegister;
    dispatch({ type: actions.OPEN_MODAL });
  };

  const closeModal = () => {
    dispatch({ type: actions.CLOSE_MODAL });
  };

  const openCancelModal = () => {
    dispatch({ type: actions.OPEN_CANCEL_MODAL });
  };

  const closeCancelModal = () => {
    dispatch({ type: actions.CLOSE_CANCEL_MODAL });
  };

  return { ...state, openModal, closeModal, openCancelModal, closeCancelModal };
};

export default useModal;