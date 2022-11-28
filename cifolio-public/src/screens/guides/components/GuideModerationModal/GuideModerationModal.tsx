import UpdateModal from "../../../../reusableComponents/UpdateModal/UpdateModal";
import {TravelGuideDataModel} from "../../../../services/guide/interfaces";
import {createRef, LegacyRef, RefObject, useEffect, useState} from "react";
import {updateStateOnInputChange} from "../../../../utils/InputManager";
import {Multiselect} from "multiselect-react-dropdown";
import "./GuideModerationModal.css";
import {fetchCities} from "../../../../services/city/controller";
import {CityModel} from "../../../../services/city/interfaces";

interface ModalProps {
    setIsModalOpen: (isOpen: boolean) => void;
    guideToUpdate: TravelGuideDataModel;
    updateGuides: (guide: TravelGuideDataModel) => void;
}

const GuideModerationModal = (props: ModalProps) => {
    const [guideToUpdate, setGuideToUpdate] = useState<TravelGuideDataModel>(props.guideToUpdate);
    const [selectableCities, setSelectableCities] = useState<CityModel[]>([]);
    const multiSelectRef = createRef() as RefObject<Multiselect>;

    const isValidInput = () => {
        return Boolean(
            guideToUpdate.name &&
            guideToUpdate.name.trim()
        );
    }

    const handleSave = () => {
        setSelectedCitiesToGuide();
        props.updateGuides(guideToUpdate);
        props.setIsModalOpen(false);
    }

    const prepareComponent = async () => {
        const cities = await fetchCities();
        setSelectableCities(cities);
    }

    useEffect(() => {
        prepareComponent();
    }, [])

    const setSelectedCitiesToGuide = () => {
        const selectedCities = multiSelectRef.current?.getSelectedItems() as CityModel[];
        const _guide = guideToUpdate;
        _guide.cities = [...selectedCities];
        setGuideToUpdate(_guide);
    }

    return (
        <UpdateModal props={{
            isValidInput: isValidInput,
            handleSave: handleSave,
            setIsOpenModal: props.setIsModalOpen}
        }>

        <p>Guide Name</p>
        <input
            value={guideToUpdate.name}
            type={"text"}
            name={"name"}
            onChange={event => updateStateOnInputChange(event, setGuideToUpdate)}
        />

        <Multiselect
            ref={multiSelectRef}
            showCheckbox={true}
            placeholder={'Select city to add...'}
            options={selectableCities}
            selectedValues={[...guideToUpdate.cities]}
            displayValue={'name'}
        />
        </UpdateModal>
    )
}

export default GuideModerationModal;